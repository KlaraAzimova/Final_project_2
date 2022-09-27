package peaksoft.dto.register;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterResponse {

    private String id;
    private String email;
    private String firstName;
    private Role role;
    private String token;
}
