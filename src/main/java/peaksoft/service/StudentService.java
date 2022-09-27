package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.StudentAssignRequest;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.responses.SimpleResponse;
import peaksoft.responses.StudentResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.entity.User;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;

import peaksoft.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final CourseRepository courseRepository;

    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = mapToEntity(studentRequest);
        Company company = companyRepository.findById(studentRequest.getCompanyId())
                .orElseThrow(() -> new NotFoundException("company with id: " + studentRequest.getCompanyId() + " does not exists"));
        Course course = courseRepository.findById(studentRequest.getCourseId()).get();
        student.setCourse(course);
        company.addStudent(student);
        student.setCompany(company);
        User user = new User();
        user.setFirstName(studentRequest.getFirstName());
        user.setEmail(studentRequest.getEmail());
        user.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
//        List<Role> roles = findRoleByRoleName("STUDENT");
//        user.setRoles(roles);
        student.setUser(user);
        studentRepository.save(student);
        return mapToView(student);
    }

    public StudentResponse findById(Long id) {
        Student student = getById(id);
        return mapToView(student);
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student = getById(id);
        convertToUpdate(student, studentRequest);
        studentRepository.save(student);
        return mapToView(student);
    }

    public SimpleResponse deleteInstructorById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("student with id " + id + " not found!");
        }
        Student student = getById(id);
        studentRepository.delete(student);
        return new SimpleResponse(
                "DELETED",
                "student with id " + id + "deleted successfully"
        );
    }

    public List<StudentResponse> findAll() {
        return convertAllToView(studentRepository.findAll());

    }

    public SimpleResponse assignStudentToCourse(StudentAssignRequest studentRequest) {
        Student student = getById(studentRequest.getStudentId());
        Course course = courseRepository.findById(studentRequest.getCourseId()).orElseThrow(() -> new NotFoundException("course with id: " + studentRequest.getCourseId() + "not found"));
        course.addStudent(student);
        student.setCourse(course);
        studentRepository.save(student);
        return new SimpleResponse(
                "ASSIGNED",
                "assigned successfully"
        );
    }

    private Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("student with id: " + id + " not found !"));
    }

    public StudentResponse mapToView(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getStudentId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setCreated(LocalDate.now());
        response.setStudyFormat(student.getStudyFormat());
        response.setEnabled(true);
        return response;
    }

    public Student mapToEntity(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentId(studentRequest.getCompanyId());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;

    }

    public Student convertToUpdate(Student student, StudentRequest studentRequest) {
        student.setFirstName(studentRequest.getFirstName());

        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;
    }

    public List<StudentResponse> convertAllToView(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(mapToView(student));
        }
        return studentResponses;
    }
}
