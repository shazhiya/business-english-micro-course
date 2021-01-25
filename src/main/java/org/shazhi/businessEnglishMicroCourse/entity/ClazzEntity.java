package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clazz", schema = "business_english")
@Data
@Accessors(chain = true)
public class ClazzEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clazzId;
    private String clazzName;
    private Integer clazzDescription;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batch;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private CurriculumEntity curriculum;

    @OneToMany(mappedBy = "clazz")
    private List<ClazzUserEntity> clazzUsers;


}
