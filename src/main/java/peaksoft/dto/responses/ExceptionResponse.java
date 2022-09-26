package peaksoft.dto.responses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter @Setter
public class ExceptionResponse {
    private String message;
    private HttpStatus httpStatus;
    private String exceptionClassName;
    public ExceptionResponse(String message, HttpStatus httpStatus, String exceptionClassName) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.exceptionClassName = exceptionClassName;
    }

    public ExceptionResponse(HttpStatus notFound, String simpleName, String message) {
    }

}
