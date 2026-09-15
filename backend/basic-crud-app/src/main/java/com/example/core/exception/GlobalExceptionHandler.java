package com.example.core.exception;

import com.example.core.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex) {

        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .detail(ex.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage()
                                : "Invalid value",
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.VALIDATION_FAILED.getCode())
                .detail(ErrorCode.VALIDATION_FAILED.getMessage())
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity
                .status(ErrorCode.VALIDATION_FAILED.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBody(
            HttpMessageNotReadableException ex) {

        ErrorCode errorCode = ErrorCode.BAD_REQUEST;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .detail("Invalid request body")
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception ex) {

        log.error("Unexpected application error", ex);

        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .detail(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }
}
