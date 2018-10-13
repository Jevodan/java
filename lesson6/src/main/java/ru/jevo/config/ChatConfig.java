package ru.jevo.config;

import lombok.Data;

@Data
public class ChatConfig {

    private Integer port = 4001;

    private int threads = 4;

    private String host = "localhost";

}
