package com.example.core.exception;

import com.example.core.enums.CoreErrorCode;
import com.example.core.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    /* ---- 1. Any module exception extends BusinessException ---- */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {

        ErrorCode code = ex.getErrorCode();

        log.warn("Business error. code={} message={}", code.getCode(), ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .code(code.getCode())
                .detail(ex.getMessage() != null ? ex.getMessage() : code.getMessage())
                .build();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }


    /* ---- 2. Bean validation ---- */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() != null
                                ? fe.getDefaultMessage()
                                : "Invalid value",
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        ErrorCode code = CoreErrorCode.VALIDATION_FAILED;

        log.warn("Validation failed. fields={}", fieldErrors.keySet());

        ErrorResponse response = ErrorResponse.builder()
                .code(code.getCode())
                .detail(code.getMessage())
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }


    /* ---- 3. Malformed JSON / unreadable body ---- */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBody(
            HttpMessageNotReadableException ex) {

        ErrorCode code = CoreErrorCode.BAD_REQUEST;

        log.warn("Invalid request body: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .code(code.getCode())
                .detail(code.getMessage())
                .build();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }



    /* ---- 4. Fallback ---- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        log.error("Unhandled exception", ex);

        ErrorCode code = CoreErrorCode.INTERNAL_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .code(code.getCode())
                .detail(code.getMessage())
                .build();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }


}
