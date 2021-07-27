package com.example.kafka.springbootkafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.springbootkafka.model.Message;
import com.example.kafka.springbootkafka.services.MessageProducer;

@RestController
@RequestMapping("/api/kafka")
public class TestController {

    private final MessageProducer producer;

    @Autowired
    public TestController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/producer-async")
    public void producerMessageAsync(@RequestBody Message message) {
        this.producer.sendAsync(message);
    }

    @PostMapping("/producer-sync")
    public void producerMessageSync(@RequestBody Message message) {
        this.producer.sendSync(message);
    }
}
