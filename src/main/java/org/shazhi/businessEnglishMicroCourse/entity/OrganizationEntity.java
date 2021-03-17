package org.shazhi.businessEnglishMicroCourse.entity;

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

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private Date createTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRoleOrganization> uros;

    private String status;

    public OrganizationEntity ignore(){
        this.uros.forEach(uro->{
            uro.setOrganization(null);
            uro.getRole().setUros(null);
            uro.getUser().setUros(null);
            uro.getUser().ignoreAttr();
        });
        return this;
    }

}
