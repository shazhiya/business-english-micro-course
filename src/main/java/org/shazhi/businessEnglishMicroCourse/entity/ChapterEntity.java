package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapter", schema = "business_english")
@Data
@Accessors(chain = true)
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chapterId;
    @Column(name = "chapter_name")
    private String chaptername;
    private String chapterCode;
    private String chapterDescription;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private CurriculumEntity curriculum;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<CoursewareEntity> coursewares;

}
