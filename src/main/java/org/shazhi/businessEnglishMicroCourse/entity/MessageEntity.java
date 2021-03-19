package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "message", schema = "business_english")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    private String messageContent;
    private String type;
    private String status;
    private String options;

    @Column(name = "message_sendtime")
    @CreatedDate
    private Timestamp messageSendTime;

    @Column(name = "message_readtime")
    @LastModifiedDate
    private Timestamp messageReadTime;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private UserEntity targetUser;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "send_id")
    private UserEntity sendUser;
}
