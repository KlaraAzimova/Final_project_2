package peaksoft.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
@AllArgsConstructor
public class CourseResponse {
    private Long courseId;
    private String course_name;
    private int duration;
    private String image;
    private String description;
    private LocalDate dateOfStart;
}
