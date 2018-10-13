package ru.jevo.cdi;

import lombok.Data;

import java.util.UUID;

@Data
public class PacketLoginTest {

    private String login;
    private String password;
    private String type;
    final public String id =UUID.randomUUID().toString();

}
