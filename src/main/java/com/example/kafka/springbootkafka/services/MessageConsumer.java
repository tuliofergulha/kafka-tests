package com.example.kafka.springbootkafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.kafka.springbootkafka.helpers.KafkaConstants;
import com.example.kafka.springbootkafka.model.Message;

@Configuration
@EnableKafka
@KafkaListener(
    id = KafkaConstants.CLIENT_ID,
    topics = KafkaConstants.TOPIC_MESSAGE
)
public class MessageConsumerMessage {

    @KafkaHandler(isDefault = true)
    public void listen(ConsumerRecord<Long, Message> record) {
        System.out.println("Mensagem recebida (handler): {id: " + record.value().getId() + ", email: " +
            record.value().getEmail() + ", value: " + record.value().getValue() + "}");
    }
}