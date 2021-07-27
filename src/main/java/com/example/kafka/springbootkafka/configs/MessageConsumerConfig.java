package com.example.kafka.springbootkafka.configs;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import com.example.kafka.springbootkafka.helpers.KafkaConstants;
import com.example.kafka.springbootkafka.helpers.MessageDeserializer;
import com.example.kafka.springbootkafka.model.Message;

@Configuration
@EnableKafka
public class MessageConsumerConfig {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Long, Message>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Message> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, Message> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
            () -> new LongDeserializer(),
            () -> new MessageDeserializer());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            KafkaConstants.KAFKA_BROKERS);
        props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            KafkaConstants.GROUP_ID_CONFIG);
        props.put(
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
            KafkaConstants.MAX_POLL_RECORDS);
        props.put(
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
            KafkaConstants.ENABLE_AUTO_COMMIT_CONFIG);
        props.put(
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
            KafkaConstants.OFFSET_RESET_EARLIER);
        return props;
    }

    @Bean
    public ConcurrentMessageListenerContainer<Long, Message> listen() {
        ConcurrentMessageListenerContainer<Long, Message>
            container = kafkaListenerContainerFactory()
            .createContainer(KafkaConstants.TOPIC_NAME);
        container.setupMessageListener(new MessageListener<Long, Message>() {
            @Override
            public void onMessage(ConsumerRecord<Long, Message> data) {
                System.out.println("Mensagem recebida (listener): {id: " + data.value().getId() + ", email: " +
                    data.value().getEmail() + ", value: " + data.value().getValue() + "}");
            }
        });
        return container;
    }
}