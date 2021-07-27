package com.example.kafka.springbootkafka.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.kafka.springbootkafka.helpers.KafkaConstants;
import com.example.kafka.springbootkafka.model.Message;

@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<Long, Message> template;

    public void sendAsync(Message message) {
        ProducerRecord<Long, Message> record = new ProducerRecord<Long, Message>(
            KafkaConstants.TOPIC_NAME, message);
        ListenableFuture<SendResult<Long, Message>> future = template.send(record);
        future.addCallback(new ListenableFutureCallback<SendResult<Long, Message>>() {
            @Override
            public void onSuccess(SendResult<Long, Message> result) {
                System.out.println("Enviado com sucesso!");
            }
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }
        });
    }

    public void sendSync(Message message) {
        ProducerRecord<Long, Message> record =
            new ProducerRecord<Long, Message>(
                KafkaConstants.TOPIC_NAME, message);
        try {
            template.send(record).get(10, TimeUnit.SECONDS);
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
