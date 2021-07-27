package com.example.kafka.springbootkafka.model;

public class Message {
    private Long id;
    private String email;
    private String value;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }
}
