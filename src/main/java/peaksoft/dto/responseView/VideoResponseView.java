package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.responses.VideoResponse;

import java.util.List;

@Getter
@Setter
public class VideoResponseView {
    List<VideoResponse> videoResponses;
}
