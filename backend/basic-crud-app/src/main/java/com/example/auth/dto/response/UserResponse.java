package com.example.auth.dto.response;

import java.time.Instant;
import java.util.Set;

public record UserResponse(Long id,
                           String email,
                           String fullName,
                           Set<String> roles,
                           Instant createdAt,
                           Instant updatedAt,
                           String createdBy,
                           String updatedBy)
{

}

