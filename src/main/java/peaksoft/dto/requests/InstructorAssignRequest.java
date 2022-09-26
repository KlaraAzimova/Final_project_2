package peaksoft.dto.requests;
import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class InstructorAssignRequest {
    private Long instructorId;
    private Long courseId;
}
