package peaksoft.dto.requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter@Setter
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}
