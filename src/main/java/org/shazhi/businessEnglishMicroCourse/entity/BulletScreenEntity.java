package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shazhi.businessEnglishMicroCourse.converter.JpaConverterJson;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bulletscreen", schema = "business_english")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BulletScreenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bulletscreen_id")
    private Integer bulletScreenId;

    @Convert(converter = JpaConverterJson.class)
    @Column(name = "bulletscreen_detail")
    private Object bulletScreenDetail;

    @Column(name = "bulletscreen_launchtime")
    private Timestamp bulletScreenLaunchTime;

    @Column(name = "bulletscreen_content")
    private String bulletScreenContent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseware_id")
    private CoursewareEntity courseware;
}
