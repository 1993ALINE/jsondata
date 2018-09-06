package com.example.jsondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity(name = "messages")
public class Message {


    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "text")
    private String text;


    public Message() {}
}
