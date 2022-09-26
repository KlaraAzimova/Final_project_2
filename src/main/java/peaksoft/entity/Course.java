package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_seq", strategy = GenerationType.SEQUENCE)
    private Long courseId;

    @JoinColumn(name = "course_name")
    private String course_name;

    @Column(name = "date_of_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    @Column
    private int duration;
    @Column
    private String image;
    @Column
    private String description;

    @ManyToMany(cascade = {MERGE, PERSIST, REFRESH, DETACH}, mappedBy = "courses")
    private List<Instructor> instructors;

    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Student> students;

    @OneToMany(cascade = ALL, mappedBy = "courses")
    private List<Lesson> lessons;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private Company company;

    public Course(String course_name, LocalDate dateOfStart, int duration, String image, String description, List<Instructor> instructors, List<Student> students, List<Lesson> lessons, Company company) {
        this.course_name = course_name;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.image = image;
        this.description = description;
        this.instructors = instructors;
        this.students = students;
        this.lessons = lessons;
        this.company = company;
    }

    public void addInstructors(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        } else {
            this.students.add(student);
        }
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        } else {
            this.lessons.add(lesson);
        }
    }

    public void addInstructor(Instructor instructor) {
    }
}

