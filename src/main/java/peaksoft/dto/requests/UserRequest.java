package peaksoft.dto.requests;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UserRequest {
    private String name;
    private String password;
    private String email;
}
