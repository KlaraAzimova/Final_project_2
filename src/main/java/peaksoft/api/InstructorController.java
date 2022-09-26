package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.InstructorAssignRequest;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/save")
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/findById/{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.findById(id);
    }

    @PutMapping("/update/{id}")
    public InstructorResponse updateInstructorById(@PathVariable Long id,
                                                   @RequestBody InstructorRequest instructorRequest) {
        return instructorService.update(id, instructorRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteInstructorById(@PathVariable Long id) {
        return instructorService.deleteInstructorById(id);
    }

    @GetMapping("/getAll")
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.findAll();
    }

    @PostMapping("/assign")
    public SimpleResponse assignInstructorToCourse(@RequestBody InstructorAssignRequest instructorAssignRequest) {
        return instructorService.assignInstructorToCourse(instructorAssignRequest);
    }

}

