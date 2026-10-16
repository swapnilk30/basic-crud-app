package com.example.employee.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUploadResponse {

    private int totalRows;
    private int successCount;
    private int failureCount;
    private List<String> errors;   // row-level error messages
}
