package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.ContactsEntity;
import org.shazhi.businessEnglishMicroCourse.entity.MessageEntity;
import org.shazhi.businessEnglishMicroCourse.repository.MessageRepository;
import org.shazhi.businessEnglishMicroCourse.service.MessageService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;

    final EntityManager em;

    public MessageServiceImpl(MessageRepository messageRepository, EntityManager em) {
        this.messageRepository = messageRepository;
        this.em = em;
    }

    @Override
    public List<MessageEntity> load(MessageEntity message) {
        return messageRepository.findAll(Example.of(message));
    }

    @Override
    public List<ContactsEntity> loadContactors(ContactsEntity con) {
        return messageRepository.loadContactors(con.getSelf().getUserId());
    }

    @Override
    public Result updateContactor(ContactsEntity contactsEntity, String operate) {
        if ("add".equals(operate)){
            em.merge(contactsEntity);
        }
        if ("del".equals(operate)){
            em.remove(messageRepository.findContacts(contactsEntity.getSelf().getUserId(),contactsEntity.getContactor().getUserId()));
        }
        return new Result().setSuccess();
    }

    @Override
    public Result markRead(MessageEntity mess) {
        messageRepository.markRead(mess.getTargetUser().getUserId(),mess.getSendUser().getUserId(),mess.getMessageSendTime());
        return new Result().setSuccess();
    }

    @Override
    public List<MessageEntity> loadHistory(MessageEntity history) {
        return messageRepository.loadHistory(
                history.getTargetUser().getUserId(),
                history.getSendUser().getUserId(),
                history.getMessageSendTime(),
                PageRequest.of(0,10)
        );
    }

    @Override
    public Result sendMessage(MessageEntity messageEntity) {
        messageEntity.setType("普通消息");
        messageEntity.setStatus("未读");
        messageRepository.save(messageEntity);
        return new Result().setSuccess();
    }

    @Override
    public MessageEntity loadLastMessage(MessageEntity last) {
        return messageRepository.lastMessage(last.getSendUser().getUserId(),last.getTargetUser().getUserId());
    }
}
