package com.example.kafka.springbootkafka.configs;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.kafka.springbootkafka.helpers.KafkaConstants;
import com.example.kafka.springbootkafka.helpers.MessageSerializer;
import com.example.kafka.springbootkafka.model.Message;

@Configuration
public class MessageProducerConfig {

    @Bean
    public KafkaTemplate<Long, Message> kafkaTemplate() {
        return new KafkaTemplate<Long, Message>(producerFactory());
    }

    @Bean
    public ProducerFactory<Long, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
            producerConfigs(),
            () -> new LongSerializer(),
            () -> new MessageSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            KafkaConstants.KAFKA_BROKERS);
        props.put(
            ProducerConfig.CLIENT_ID_CONFIG,
            KafkaConstants.CLIENT_ID);
        return props;
    }
}
