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
    private String chapterName; // elementUI - bug 
    private String chapterCode;
    private String chapterDescription;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private CurriculumEntity curriculum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<CoursewareEntity> coursewares;

}
