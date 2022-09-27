package peaksoft.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN,
    INSTRUCTOR,
    STUDENT,
    MANAGER
    ;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
