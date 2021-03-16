package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    private String data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<RoleEntity> roles;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private Date createTime;

    private String status;

    public OrganizationEntity ignore(){
        return this.setCreator(this.getCreator().ignoreAttr(this.getCreator()));
    }
}
