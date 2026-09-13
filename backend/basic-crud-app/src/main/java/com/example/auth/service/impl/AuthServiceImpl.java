package com.example.auth.service.impl;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.RefreshTokenRequest;
import com.example.auth.dto.request.RegisterRequest;
import com.example.auth.dto.response.AuthResponse;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.entity.RefreshToken;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import com.example.auth.mapper.UserMapper;
import com.example.auth.repository.RefreshTokenRepository;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.AuthService;
import com.example.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Value("${app.jwt.refresh-token-expiration-ms}")
    private long refreshExpMs;


    @Override
    @Transactional
    public UserResponse register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already registered");
        }
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Default role missing"));

        User u = User.builder()
                .email(req.email().toLowerCase())
                .password(passwordEncoder.encode(req.password()))
                .fullName(req.fullName())
                .roles(Set.of(userRole))
                .build();

        return userMapper.toResponse(userRepository.save(u));
    }


    @Override
    @Transactional
    public AuthResponse login(LoginRequest req) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        var userDetails = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        String access = jwtService.generateAccessToken(userDetails,
                Map.of("roles", user.getRoles().stream().map(Role::getName).toList()));

        String refresh = UUID.randomUUID().toString();
        refreshTokenRepository.save(RefreshToken.builder()
                .token(refresh)
                .user(user)
                .expiresAt(Instant.now().plusMillis(refreshExpMs))
                .build());

        return new AuthResponse(access, refresh, "Bearer", refreshExpMs / 1000);
    }

    @Override
    @Transactional
    public AuthResponse refresh(RefreshTokenRequest req) {
        RefreshToken rt = refreshTokenRepository.findByToken(req.refreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (rt.isRevoked() || rt.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token expired or revoked");
        }

        User user = rt.getUser();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName())).toList())
                .build();

        String newAccess = jwtService.generateAccessToken(userDetails, Map.of());

        rt.setRevoked(true);
        String newRefresh = UUID.randomUUID().toString();
        refreshTokenRepository.save(RefreshToken.builder()
                .token(newRefresh)
                .user(user)
                .expiresAt(Instant.now().plusMillis(refreshExpMs))
                .build());

        return new AuthResponse(newAccess, newRefresh, "Bearer", refreshExpMs / 1000);
    }

    @Override
    @Transactional
    public void logout(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken)
                .ifPresent(rt -> rt.setRevoked(true));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse me(String email) {
        return userMapper.toResponse(
                userRepository.findByEmail(email).orElseThrow());
    }



}
