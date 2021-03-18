package org.shazhi.businessEnglishMicroCourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "security", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class SecurityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer securityId;
    private String securityName;
    private String scope;

    @ManyToMany(mappedBy = "securities")
    @JsonIgnore
    private List<RoleEntity> roleSecurities;

    public SecurityEntity ignore(){
        return new SecurityEntity()
                .setSecurityId(securityId)
                .setSecurityName(getSecurityName())
                .setScope(scope);
    }
}
