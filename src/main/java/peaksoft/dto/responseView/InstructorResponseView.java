package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.responses.InstructorResponse;

import java.util.List;

@Getter
@Setter
public class InstructorResponseView {
    List<InstructorResponse> instructorResponses;
}
