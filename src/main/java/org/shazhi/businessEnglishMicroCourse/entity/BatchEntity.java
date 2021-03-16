package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "batch", schema = "business_english")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchId;
    private String batchName;
    @Column(name = "batch_starttime")
    private Timestamp batchStartTime;
    @Column(name = "batch_endtime")
    private Timestamp batchEndTime;

    @OneToMany
    private List<ClazzEntity> clazzesByBatchId;
}
