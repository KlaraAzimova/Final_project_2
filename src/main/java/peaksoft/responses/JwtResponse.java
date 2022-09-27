package peaksoft.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

@Getter @Setter
public class JwtResponse {

    private Long userId;
    private String email;
    private String jwt;
    private Role role;

    public JwtResponse(Long userId, String email, String jwt, Role role) {
        this.userId = userId;
        this.email = email;
        this.jwt = jwt;
        this.role = role;
    }
}
