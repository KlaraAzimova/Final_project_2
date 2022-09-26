package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
    private Long companyId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country♥")
    private String locatedCountry;
    @Column(name = "created_at")
    private LocalDate createAt;
    @Column(name = "is_active")
    private boolean isActive = true;
    @OneToMany(cascade = ALL, mappedBy = "company")
    private List<Course> courses;
    @OneToMany(cascade = ALL, mappedBy = "company")
    private List<Instructor> instructors;

    @OneToMany(cascade = ALL, mappedBy = "company")
    private List<Student> students;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        } else {
            this.students.add(student);
        }
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            this.courses.add(course);
        }
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }
}