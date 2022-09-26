package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long instructorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column
    private String specialization;
    @ManyToMany(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    @JoinTable
    private List<Course> courses;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST})
    private Company company;

    @OneToOne(cascade = ALL)
    private User user;

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization, List<Course> courses, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
        this.courses = courses;
        this.company = company;
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            this.courses.add(course);
        }
    }

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }
}

