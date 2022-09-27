package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.responses.LessonResponse;

import java.util.List;

@Getter
@Setter
public class LessonResponseView {
    List<LessonResponse> lessonResponses;
}
