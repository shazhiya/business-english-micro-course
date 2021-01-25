package org.shazhi.businessEnglishMicroCourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "security", schema = "business_english")
@Data
@Accessors(chain = true)
public class SecurityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer securityId;
    private String securityName;

    @ManyToMany(mappedBy = "securities")
    @JsonIgnore
    private List<RoleEntity> roleSecurities;
}
