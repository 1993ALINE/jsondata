package com.example.jsondata;

import com.example.jsondata.model.Message;
import com.example.jsondata.service.MessageService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class JsondataApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsondataApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MessageService messageService){
        return args -> {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            TypeReference<List<Message>> typeReference = new TypeReference<List<Message>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("researchapp-e2260-firebase-adminsdk-lf321-fa2d0f6665.json");

            FileInputStream serviceAccount = new FileInputStream("researchapp-e2260-firebase-adminsdk-lf321-fa2d0f6665.json");
            try{
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://researchapp-e2260.firebaseio.com/")
                        .build();

                List<Message> messages = mapper.readValue(inputStream,typeReference);
                messageService.save(messages);
                System.out.println("Messages Saved!");

                FirebaseApp.initializeApp(options);
            }catch (Exception e){
                System.out.println("Unable to save messages: " + e.getMessage());
            }

        };
    }

}
