package com.example.KafkaProducer.Kafka_Producer.controller;

import com.example.KafkaProducer.Kafka_Producer.service.kafkaMessagePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    private  final kafkaMessagePublisherService publisherService;

    public EventController(kafkaMessagePublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try{ publisherService.sendMesssageToTopics(message);
            return ResponseEntity.ok("successfully send data");}
        catch (Exception ex){
return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
