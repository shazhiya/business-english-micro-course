package org.shazhi.businessEnglishMicroCourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role_organization", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserRoleOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer uroId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    RoleEntity role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    OrganizationEntity organization;

}
