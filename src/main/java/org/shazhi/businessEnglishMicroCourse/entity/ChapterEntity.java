package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chapter", schema = "business_english")
@Getter
@Setter
@NoArgsConstructor
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
