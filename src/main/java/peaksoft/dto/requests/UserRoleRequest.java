package peaksoft.dto.requests;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

@Getter
@Setter
public class UserRoleRequest {

    private Role role;
}
