package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String userName;
    private String password;
    @Column(unique = true)
    private String userEmail;
    private String userHeadicon;
    @CreationTimestamp
    private Date userRegisterdate;
    private String userIntro;
    private String userTelephone;
    @ColumnDefault("true")
    private Boolean userEnable;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "user")
    private List<BulletScreenEntity> bulletScreens;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "user")
    private List<ClazzUserEntity> enrollClazzies;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "commenter")
    private List<CommentEntity> comments;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "creator")
    private List<CurriculumEntity> curriculums;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "sendUser")
    private List<MessageEntity> sendMessages;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "targetUser")
    private List<MessageEntity> acceptMessages;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "user")
    private List<NoteEntity> notes;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRoleOrganization> uros;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "self")
    private List<ContactsEntity> selfList;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "contactor")
    private List<ContactsEntity> contactors;

    public UserEntity ignoreAttr() {
        return new UserEntity()
                .setUserId(this.getUserId())
                .setUserName(this.getUserName())
                .setUserEnable(this.getUserEnable())
                .setUserEmail(this.getUserEmail())
                .setUserIntro(this.getUserIntro())
                .setUserRegisterdate(this.getUserRegisterdate())
                .setUserTelephone(this.getUserTelephone())
                .setUserHeadicon(this.getUserHeadicon());
    }
}
