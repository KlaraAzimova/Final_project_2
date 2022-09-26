package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    private Long lessonId;
    @Column(name = "lesson_name")
    private String lessonName;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    @JoinColumn(name = "course_id")
    private Course courses;

    @OneToMany(cascade = ALL, mappedBy = "lessons")
    private List<Task> tasks;

    @OneToOne(cascade = ALL, mappedBy = "lesson")
    private Video video;

    public void addTasks(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        } else {
            this.tasks.add(task);
        }
    }

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }
}

