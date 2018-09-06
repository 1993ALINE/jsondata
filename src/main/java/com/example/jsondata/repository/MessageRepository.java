package com.example.jsondata.repository;

import com.example.jsondata.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long> {
}
