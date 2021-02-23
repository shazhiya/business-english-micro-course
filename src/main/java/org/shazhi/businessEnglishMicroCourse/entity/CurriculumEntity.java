package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "curriculum", schema = "business_english")
@Data
@Accessors(chain = true)
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer curriculumId;
    private String curriculumName;
    private String curriculumStatus;
    private String curriculumDescription;
    private String curriculumCover;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<ChapterEntity> chapters;

    @OneToMany(mappedBy = "curriculum")
    private List<ClazzEntity> clazzes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "curriculum")
    private List<NoteEntity> notes;

}
