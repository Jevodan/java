package ru.jevo.chat.server.service;

import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

@Data
public class Connection {

    @NotNull
    private final String id = UUID.randomUUID().toString();

    @NotNull
    private Socket socket;

    @Nullable
    private String login;

    public Connection(Socket socket) {
        this.socket = socket;
    }
}
