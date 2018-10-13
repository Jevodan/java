package ru.jevo.chat.server.event;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import java.net.Socket;



public class ServerMessageInputEvent extends ServerMessageEvent {

    public ServerMessageInputEvent(@NotNull Socket socket, @NotNull String messageJSON) {
        super(socket, messageJSON);
    }
}
