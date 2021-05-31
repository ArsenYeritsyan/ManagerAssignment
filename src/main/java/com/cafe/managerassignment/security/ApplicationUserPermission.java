package com.cafe.managerassignment.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    TABLE_READ("table:read"),
    TABLE_WRITE("table:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:read"),
    ;

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}


