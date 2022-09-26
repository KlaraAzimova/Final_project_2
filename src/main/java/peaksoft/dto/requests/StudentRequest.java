package peaksoft.dto.requests;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private Long companyId;
    private Long courseId;
    private String password;
}
