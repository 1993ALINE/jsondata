package com.example.jsondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@Entity
public class Message {


    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )


    private Long id;

    private String text;


    public Message() {}
}
