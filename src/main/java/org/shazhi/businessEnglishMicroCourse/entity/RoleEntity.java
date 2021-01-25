package org.shazhi.businessEnglishMicroCourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role", schema = "business_english")
@Data
@Accessors(chain = true)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;

    @ManyToMany
    @JoinTable(name = "role_security", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "security_id")})
    private List<SecurityEntity> securities;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<UserEntity> users;

    public static RoleEntity ignoreAttr(RoleEntity role) {
        return new RoleEntity()
                .setRoleId(role.getRoleId())
                .setRoleName(role.getRoleName());
    }
}
