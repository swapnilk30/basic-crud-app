package com.example.auth.mapper;

import com.example.auth.dto.request.RegisterRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.dto.response.UserSummaryResponse;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /* ============================================================
     *  Full response (single user detail view)
     * ============================================================ */
    public UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                extractRoleNames(user),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getCreatedBy(),
                user.getUpdatedBy()
        );
    }

    /* ============================================================
     *  Summary response (list / table view)
     * ============================================================ */
    public UserSummaryResponse toSummary(User user) {
        if (user == null) return null;
        return new UserSummaryResponse(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.isEnabled(),
                extractRoleNames(user)
        );
    }

    /* ============================================================
     *  Bulk conversions
     * ============================================================ */
    public List<UserSummaryResponse> toSummaryList(List<User> users) {
        if (users == null) return Collections.emptyList();
        return users.stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }

    public Page<UserSummaryResponse> toSummaryPage(Page<User> page) {
        if (page == null) return Page.empty();
        return page.map(this::toSummary);
    }

    /* ============================================================
     *  Shared helper
     * ============================================================ */
    private Set<String> extractRoleNames(User user) {
        Set<Role> roles = user.getRoles();
        if (roles == null || roles.isEmpty()) return Collections.emptySet();
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet());
    }



    /** Request → Entity (for register). Password is set separately by the service. */
    public User toEntity(RegisterRequest req) {
        if (req == null) return null;
        return User.builder()
                .email(req.email() == null ? null : req.email().toLowerCase())
                .fullName(req.fullName())
                .build();
    }



    /** Convert an entire page of entities. */
    public Page<UserResponse> toResponsePage(Page<User> page) {
        return page.map(this::toResponse);
    }


}
