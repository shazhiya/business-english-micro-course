package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "curriculum", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer curriculumId;
    private String curriculumName;
    private String curriculumStatus;
    private String curriculumDescription;
    private String curriculumCover;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<ChapterEntity> chapters;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "curriculum")
    private List<ClazzEntity> clazzes;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "curriculum")
    private List<NoteEntity> notes;

}
