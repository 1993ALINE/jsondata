package com.example.jsondata.service;


import com.example.jsondata.model.Message;
import com.example.jsondata.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;



    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Iterable<Message> list() {
        return messageRepository.findAll();
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public void save(List<Message> messages) {
        messageRepository.save(messages);
    }

}
