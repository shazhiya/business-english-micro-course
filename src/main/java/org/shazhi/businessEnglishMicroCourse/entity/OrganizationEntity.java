package org.shazhi.businessEnglishMicroCourse.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "organization", schema = "business_english")
@Accessors(chain = true)
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizationId;
    private String organizationName;
    private String organizationDescription;
    private String phone;
    private String data;
    private String status;
    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private Date createTime;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER, mappedBy = "organization")
    private List<UserRoleOrganization> uros;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "organization")
    private List<CurriculumEntity> curriculums;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "organization")
    private List<ClazzEntity> classes;

    @JSONField(serialize = false)
    public UserEntity getCreator(){
        UserEntity creator = null;
        for (UserRoleOrganization uro : this.getUros()) {
            if (uro.getRole()==null) continue;
            if (uro.getRole().getRoleName().equals(this.getOrganizationName()+"-创建者")) {
                creator = uro.getUser();
            }
        }

        return creator;
    }


}
