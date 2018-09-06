package com.example.jsondata.controller;


import com.example.jsondata.model.Message;
import com.example.jsondata.service.MessageService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")

public class MessageController {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("messages");
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/list")
    public Iterable<Message> list() {
        return messageService.list();
    }
}
