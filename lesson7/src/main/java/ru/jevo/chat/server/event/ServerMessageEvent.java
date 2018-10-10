package ru.jevo.chat.server.event;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

@Data
public class ServerMessageEvent {

    @NotNull
    private final Socket socket;

    @NotNull
    private final String messageJSON;

    public ServerMessageEvent(Socket socket, String messageJSON) {
        this.socket = socket;
        this.messageJSON = messageJSON;
    }
}
