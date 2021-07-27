package com.example.kafka.springbootkafka.helpers;

public interface KafkaConstants {
    public static String KAFKA_BROKERS = "localhost:9092";
    public static String CLIENT_ID = "client1";
    public static String TOPIC_NAME = "message";
    public static String GROUP_ID_CONFIG = "consumerGroup1";
    public static String OFFSET_RESET_EARLIER = "earliest";
    public static Integer MAX_POLL_RECORDS = 1;
    public static String ENABLE_AUTO_COMMIT_CONFIG = "false";
}
