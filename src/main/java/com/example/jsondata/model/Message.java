package com.example.jsondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSendAt() {
        return sendAt;
    }

    public void setSendAt(String sendAt) {
        this.sendAt = sendAt;
    }
}
