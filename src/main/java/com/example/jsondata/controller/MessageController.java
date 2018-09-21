package com.example.jsondata.controller;


import com.example.jsondata.model.Message;
import com.example.jsondata.service.MessageService;
import com.example.jsondata.tools.Log;
import com.google.firebase.database.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/messages")
public class MessageController {


    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public String showListOfMessage(Model model){

        model.addAttribute("listOfMessage",messageService.findAll());

        return "messageListPage";
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity showListOfMessagePage(@RequestParam(value = "text",defaultValue = "")String text) {
        Log.info("show  Message list");
        Message message = new Message();
        message.setText(text);
        message.setSendAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ListOfMessage").push();
        message.setId(ref.getKey());
        messageService.save(message);
        ref.setValue(message,( databaseError,  databaseReference)->{
            if(databaseError==null) {
                Log.info("Message Save Success");
            }else
                Log.error("failed to save");
        });
        return new ResponseEntity(HttpStatus.OK);
    }
}
