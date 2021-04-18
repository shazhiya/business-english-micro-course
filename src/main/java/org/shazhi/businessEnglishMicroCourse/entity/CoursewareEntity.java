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
@Table(name = "courseware", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CoursewareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coursewareId;
    private String coursewareDescription;
    private String coursewareName;
    private String coursewarePath;
    private String filetype;
    private String md5;
    private String md5Salted;
    private Long totalSize;
    private Integer fragmentNbr;
    private Long fragmentSize;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

}
