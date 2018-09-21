package com.example.jsondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity(name = "messages")
public class Message {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "text")
    private String text;

    @Column(name = "send_at")
    private String sendAt;

    public Message() {}
}
