package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.ContactsEntity;
import org.shazhi.businessEnglishMicroCourse.entity.MessageEntity;
import org.shazhi.businessEnglishMicroCourse.service.MessageService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("load")
    public List<MessageEntity> loadMessage(@RequestBody MessageEntity message){
        return messageService.load(message);
    }

    @RequestMapping("history")
    public List<MessageEntity> history(@RequestBody MessageEntity history){
        return messageService.loadHistory(history);
    }

    @RequestMapping("send")
    public Result sendMessage(@RequestBody MessageEntity messageEntity){
        return messageService.sendMessage(messageEntity);
    }

    @RequestMapping("markRead")
    public Result markRead(@RequestBody MessageEntity mess){
        return messageService.markRead(mess);
    }

    @RequestMapping("contactors")
    public List<ContactsEntity> loadContactors(@RequestBody ContactsEntity con){
        return messageService.loadContactors(con);
    }

    @RequestMapping("contactors/{operate}")
    public Result updateContactor(@RequestBody ContactsEntity contactsEntity,@PathVariable String operate){
        return messageService.updateContactor(contactsEntity,operate);
    }
}
