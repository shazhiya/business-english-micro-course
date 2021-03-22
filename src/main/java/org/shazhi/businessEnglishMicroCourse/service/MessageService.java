package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.ContactsEntity;
import org.shazhi.businessEnglishMicroCourse.entity.MessageEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface MessageService {

    List<MessageEntity> load(MessageEntity message);

    List<ContactsEntity> loadContactors(ContactsEntity con);

    Result updateContactor(ContactsEntity contactsEntity, String operate);

    Result markRead(MessageEntity mess);

    List<MessageEntity> loadHistory(MessageEntity history);

    Result sendMessage(MessageEntity messageEntity);

}
