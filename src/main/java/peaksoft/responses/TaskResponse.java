package peaksoft.responses;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadline;
}
