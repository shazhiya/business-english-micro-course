package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "filetype", schema = "business_english")
@Data
@Accessors(chain = true)
public class FiletypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filetypeId;
    private String filetypeName;
    private String filetypeDescription;

    @OneToMany(mappedBy = "filetype")
    private List<CoursewareEntity> coursewares;
}
