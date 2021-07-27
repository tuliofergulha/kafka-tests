package com.example.kafka.springbootkafka.helpers;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.kafka.springbootkafka.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDeserializer implements Deserializer<Message> {

    @Override
    public Message deserialize(String topic, byte[] data){
        ObjectMapper mapper = new ObjectMapper();
        Message message = null;
        try {
            message = mapper.readValue(data, Message.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
