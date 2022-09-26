package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Company;

@Getter@Setter
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Company companyId;

}
