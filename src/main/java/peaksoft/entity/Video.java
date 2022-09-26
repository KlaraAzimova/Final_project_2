package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
public class Video {
    @Id
    @SequenceGenerator(name = "video_seq", sequenceName = "video_seq", allocationSize = 1)
    @GeneratedValue(generator = "video_seq", strategy = GenerationType.SEQUENCE)
    private Long videoId;
    @Column(name = "video_name")
    private String videoName;
    @Column()
    private String link;
    @OneToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private Lesson lesson;

    public Video(String videoName, String link) {
        this.videoName = videoName;
        this.link = link;
    }
}
