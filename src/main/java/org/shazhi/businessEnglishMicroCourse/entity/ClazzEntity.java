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
@Table(name = "clazz", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ClazzEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clazzId;
    private String clazzName;
    private String clazzLogo;
    private String clazzDescription;
    private String status;

    @OneToMany(mappedBy = "clazz",cascade = CascadeType.ALL)
    private List<ClassCurriculum> ccs;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "clazz")
    private List<ClazzUserEntity> clazzUsers;

}
