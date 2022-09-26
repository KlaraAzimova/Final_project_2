package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StudentAssignRequest;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/save")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/findById/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/update/{id}")
    public StudentResponse updateStudentById(@PathVariable Long id,
                                             @RequestBody StudentRequest studentRequest) {
        return studentService.update(id, studentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteInstructorById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping("/assign")
    public SimpleResponse assignStudentToCourse(@RequestBody StudentAssignRequest studentAssignRequest) {
        return studentService.assignStudentToCourse(studentAssignRequest);
    }
}