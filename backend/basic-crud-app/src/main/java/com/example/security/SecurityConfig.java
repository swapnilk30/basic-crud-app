package com.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Environment env) throws Exception {

        boolean isDev = env.matchesProfiles("dev");

        http
                .csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))  // for H2 console
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(auth -> auth
                        // 1. Public endpoints
                        .requestMatchers(ApiEndpoints.Public.ALL).permitAll()
                        // 2. Dev-only (profile-gated)
                        .requestMatchers(ApiEndpoints.Dev.H2_CONSOLE).permitAll()
                        // 3. Admin-only
                        .requestMatchers(ApiEndpoints.Admin.ALL).hasRole("ADMIN")
                        // 4. Role-based business endpoints
                        .requestMatchers(ApiEndpoints.Business.EMPLOYEES)
                        .hasAnyRole("ADMIN", "HR")
                        .requestMatchers(ApiEndpoints.Business.REPORTS)
                        .hasAnyRole("ADMIN", "ANALYST")
                        .requestMatchers(ApiEndpoints.Business.MERCHANTS)
                        .hasAnyRole("ADMIN", "MERCHANT_MANAGER")

                        // 5. Everything else requires authentication
                        .anyRequest().authenticated())

                // 👇 No authenticationProvider() call — Spring auto-detects:
                //    - UserDetailsService bean
                //    - PasswordEncoder bean
                // and builds a DaoAuthenticationProvider under the hood.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
