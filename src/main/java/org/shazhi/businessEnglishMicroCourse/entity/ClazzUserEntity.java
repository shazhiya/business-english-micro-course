package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "clazz_user")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ClazzUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clazzUserId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "clazz_id")
    private ClazzEntity clazz;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clazzUser")
    private List<ProgressEntity> progresses;

}
