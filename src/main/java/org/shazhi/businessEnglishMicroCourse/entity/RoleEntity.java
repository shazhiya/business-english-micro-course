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
@Table(name = "role", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;

    @ManyToMany
    @JoinTable(name = "role_security", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "security_id")})
    private List<SecurityEntity> securities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRoleOrganization> uros;

    public static RoleEntity ignoreAttr(RoleEntity role) {
        return new RoleEntity()
                .setRoleId(role.getRoleId())
                .setRoleName(role.getRoleName());
    }
}
