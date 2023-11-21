package com.example.spring_jobs.user;


//사용자의 권한을 관리하는 Enum Class
public enum UserRoleEnum {
    USER(Authority.USER),  // 개인 권한
    ADMIN(Authority.COMPANY);  // 기업 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String COMPANY = "ROLE_COMPANY";
    }
}