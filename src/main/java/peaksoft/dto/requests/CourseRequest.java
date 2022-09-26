package peaksoft.dto.requests;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CourseRequest {
    private String course_name;
    private int duration;
    private String image;
    private String description;
    private LocalDate dateOfStart;
    private Long companyId;
}
