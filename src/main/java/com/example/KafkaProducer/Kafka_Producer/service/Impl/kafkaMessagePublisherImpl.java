package com.example.KafkaProducer.Kafka_Producer.service.Impl;


import com.example.KafkaProducer.Kafka_Producer.service.kafkaMessagePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class kafkaMessagePublisherImpl implements kafkaMessagePublisherService {

    private final KafkaTemplate<String,Object> template;

    public kafkaMessagePublisherImpl(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @Override
    public void sendMesssageToTopics(String message) {
            CompletableFuture<SendResult<String, Object>> future = template.send("kafka-demo1", message);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("send message " + result.getRecordMetadata().offset());
            }else{
                System.out.println("error message" + ex.getMessage());
            }
        });

    }
}
