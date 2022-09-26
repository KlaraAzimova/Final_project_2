package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/save")
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(taskRequest);
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'STUDENT')")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PutMapping("/update/{id}")
    public TaskResponse updateTaskById(@PathVariable Long id,
                                       @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    public List<TaskResponse> getAllTasks() {
        return taskService.findAll();
    }
}
