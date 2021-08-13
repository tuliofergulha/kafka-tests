package com.example.kafka.springbootkafka.helpers;

import org.apache.kafka.common.serialization.Serializer;

import com.example.kafka.springbootkafka.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageSerializer implements Serializer<Message> {

    @Override
    public byte[] serialize(String topic, Message message){
        byte[] data = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            data = objectMapper.writeValueAsString(message).getBytes();
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
