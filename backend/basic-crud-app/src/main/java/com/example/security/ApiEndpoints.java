package com.example.security;

/**
 * Single source of truth for all public/protected endpoint paths.
 * Grouped by concern. Referenced from SecurityConfig AND controllers.
 */
public final class ApiEndpoints {

    private ApiEndpoints(){

    }

    /* ============ Public (no auth) ============ */
    public static final class Public {
        private Public() {}

        public static final String[] AUTH = {
                "/api/auth/register",
                "/api/auth/login",
                "/api/auth/refresh",
                "/api/auth/forgot-password",
                "/api/auth/reset-password"
        };

        public static final String[] DOCS = {
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**"
        };

        public static final String[] HEALTH = {
                "/actuator/health",
                "/actuator/health/**",
                "/actuator/info"
        };

        public static final String[] STATIC = {
                "/", "/index.html", "/favicon.ico",
                "/assets/**", "/static/**", "/public/**"
        };

        public static final String[] ALL = concat(AUTH, DOCS, HEALTH, STATIC);

    }

    /* ============ Admin only ============ */
    public static final class Admin {
        private Admin() {}

        public static final String[] USERS = { "/api/admin/users/**" };
        public static final String[] MERCHANTS = { "/api/admin/merchants/**" };
        public static final String[] ACTUATOR_FULL = { "/actuator/**" };

        public static final String[] ALL = concat(USERS, MERCHANTS, ACTUATOR_FULL);
    }

    /* ============ Role-scoped business endpoints ============ */
    public static final class Business {
        private Business() {}

        public static final String[] EMPLOYEES = { "/api/employees/**" };
        public static final String[] REPORTS   = { "/api/reports/**" };
        public static final String[] MERCHANTS = { "/api/merchants/**" };
    }

    /* ============ Dev-only ============ */
    public static final class Dev {
        private Dev() {}
        public static final String[] H2_CONSOLE = { "/h2-console/**" };
    }

    /* ============ Utility ============ */
    private static String[] concat(String[]... arrays) {
        int total = 0;
        for (String[] a : arrays) total += a.length;
        String[] out = new String[total];
        int i = 0;
        for (String[] a : arrays) {
            System.arraycopy(a, 0, out, i, a.length);
            i += a.length;
        }
        return out;
    }
}
