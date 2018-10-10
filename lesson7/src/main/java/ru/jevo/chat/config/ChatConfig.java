package ru.jevo.chat.config;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;

@Data
@ApplicationScoped
public class ChatConfig {

    private Integer port = 4001;

    private String host = "localhost";

}