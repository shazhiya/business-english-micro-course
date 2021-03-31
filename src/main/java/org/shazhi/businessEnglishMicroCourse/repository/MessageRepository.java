package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.ContactsEntity;
import org.shazhi.businessEnglishMicroCourse.entity.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface  MessageRepository extends JpaRepository<MessageEntity,Integer>,JpaSpecificationExecutor<MessageEntity> {

    @Query("select con from ContactsEntity con where con.self.userId = :selfId")
    List<ContactsEntity> loadContactors(@Param("selfId") Integer selfId);

    @Modifying
    @Query("update MessageEntity mess set mess.status = '已读' where mess.targetUser.userId =:tid and mess.sendUser.userId=:sid and mess.messageSendTime <= :stime")
    Integer markRead(@Param("tid") Integer userId,@Param("sid") Integer userId1,@Param("stime") Timestamp messageSendTime);

    @Query("from MessageEntity mess where ((mess.targetUser.userId =:tid and mess.sendUser.userId=:sid) or (mess.targetUser.userId =:sid and mess.sendUser.userId=:tid)) and mess.messageSendTime <= :stime order by mess.messageSendTime desc")
    List<MessageEntity> loadHistory(@Param("tid") Integer userId,@Param("sid") Integer userId1,@Param("stime") Timestamp sendTime, Pageable page);

    @Query("from ContactsEntity con where con.self.userId =:sid and con.contactor.userId = :cid")
    ContactsEntity findContacts(@Param("sid") Integer userId,@Param("cid") Integer userId1);

    @Query("from MessageEntity mess where mess.sendUser.userId=:sid and mess.targetUser.userId=:tid order by mess.messageSendTime desc")
    MessageEntity lastMessage(@Param("sid") Integer userId,@Param("tid") Integer userId1);
}
