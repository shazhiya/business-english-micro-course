package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user", schema = "business_english")
@Data
@Accessors(chain = true)
@SelectBeforeUpdate
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String userName;
    private String password;
    @Column(unique = true)
    private String userEmail;
    private String userHeadico;
    @CreationTimestamp
    private Date userRegisterdate;
    private String userIntro;
    private String userTelephone;
    @ColumnDefault("true")
    private Boolean userEnable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BulletScreenEntity> bulletScreens;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ClazzUserEntity> enrollClazzies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commenter")
    private List<CommentEntity> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CurriculumEntity> curriculums;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sendUser")
    private List<MessageEntity> sendMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "targetUser")
    private List<MessageEntity> acceptMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<NoteEntity> notes;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<OrganizationEntity> organizations;

    public static UserEntity ignoreAttr(UserEntity user) {
        return new UserEntity()
                .setUserId(user.getUserId())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setUserEnable(user.getUserEnable())
                .setUserEmail(user.getUserEmail())
                .setUserIntro(user.getUserIntro())
                .setUserRegisterdate(user.getUserRegisterdate())
                .setUserTelephone(user.getUserTelephone())
                .setUserHeadico(user.getUserHeadico())
                .setOrganizations(user.getOrganizations())
                .setRoles(user.getRoles()
                        .stream().map(role -> new RoleEntity().setRoleId(role.getRoleId()).setRoleName(role.getRoleName())).collect(Collectors.toList()));
    }
}
