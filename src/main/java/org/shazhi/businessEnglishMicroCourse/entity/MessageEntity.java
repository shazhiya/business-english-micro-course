package org.shazhi.businessEnglishMicroCourse.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "business_english")
@Data
@Accessors(chain = true)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    private String messageContent;

    @Column(name = "message_sendtime")
    private Timestamp messageSendTime;

    @Column(name = "message_readtime")
    private Timestamp messageReadTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private UserEntity targetUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "send_id")
    private UserEntity sendUser;
}
