package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courseware", schema = "business_english")
@Data
@Accessors(chain = true)
public class CoursewareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coursewareId;
    private String coursewareDescription;
    private String coursewareName;
    private String coursewarePath;
    private String md5;
    private String md5Salted;
    private Long totalSize;
    private Integer fragmentNbr;
    private Long fragmentSize;

    @OneToMany(mappedBy = "courseware")
    private List<BulletScreenEntity> bulletScreens;

    @OneToMany(mappedBy = "courseware")
    private List<CommentEntity> comments;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

    @ManyToOne
    @JoinColumn(name = "filetype_id")
    private FiletypeEntity filetype;

    @OneToMany(mappedBy = "courseware")
    private List<ProgressEntity> progresses;


}
