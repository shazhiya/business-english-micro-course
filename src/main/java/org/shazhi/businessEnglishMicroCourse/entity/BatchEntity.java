package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "batch", schema = "business_english")
@Data
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
