package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.MessageEntity;

import java.util.List;

public interface MessageService {
    List<MessageEntity> load(MessageEntity message);
}
