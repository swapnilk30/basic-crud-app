package com.example.employee.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    //@NotBlank(message = "Name is required")
    //@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    //@NotBlank(message = "Email is required")
    //@Email(message = "Invalid email format")
    private String email;

    /*@Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone must contain exactly 10 digits"
    )*/
    private String phone;

    //@NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    //@NotNull(message = "Joining date is required")
    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    private LocalDate resignationDate;
}
