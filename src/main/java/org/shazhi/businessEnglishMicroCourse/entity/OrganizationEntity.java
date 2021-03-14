package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "organization", schema = "business_english")
@Accessors(chain = true)
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizationId;

    private String organizationName;
    private String organizationDescription;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    private String data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<RoleEntity> roles;

    @CreatedDate
    private Date  createTime;

    private String status;
}
