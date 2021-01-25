package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.shazhi.businessEnglishMicroCourse.converter.JpaConverterJson;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comment", schema = "business_english")
@Data
@Accessors(chain = true)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentContent;

    @Convert(converter = JpaConverterJson.class)
    private Object commentDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity commenter;

    @ManyToOne
    @JoinColumn(name = "courseware_id")
    private CoursewareEntity courseware;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CommentEntity> children;

    @ManyToOne
    @JoinColumn(name = "commenttype_id")
    private CommentTypeEntity commentType;
}
