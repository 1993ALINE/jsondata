package com.example.jsondata.controller;


import com.example.jsondata.service.MessageService;
import com.example.jsondata.tools.Log;
import com.google.firebase.database.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/messages")
public class MessageController {


    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/list")
    public String showListOfMessagePage() {
        Log.info("show  Message list");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("messages");

        ref.setValueAsync("Message data ="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.info(dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.error(databaseError.toString());
            }
        });
        return "messageListPage";
    }
}
