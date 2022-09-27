package peaksoft.responses;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class VideoResponse {
    private Long id;
    private String videoName;
    private String link;
}
