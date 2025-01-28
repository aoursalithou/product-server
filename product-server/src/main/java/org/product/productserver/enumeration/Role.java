package org.product.productserver.enumeration;

import static org.product.productserver.constant.Authority.*;

public enum Role {
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_USER(USER_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}