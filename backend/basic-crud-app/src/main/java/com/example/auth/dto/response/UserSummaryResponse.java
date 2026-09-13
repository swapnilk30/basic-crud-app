package com.example.auth.dto.response;

import java.util.Set;

public record UserSummaryResponse(
        Long id,
        String email,
        String fullName,
        boolean enabled,
        Set<String> roles
) {
}
