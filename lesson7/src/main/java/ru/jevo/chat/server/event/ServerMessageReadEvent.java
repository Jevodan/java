package ru.jevo.chat.server.event;

import lombok.Data;

import javax.inject.Inject;
import java.net.Socket;

@Data
public class ServerMessageReadEvent {

    Socket socket;

    public ServerMessageReadEvent(Socket socket) {
        this.socket = socket;
    }
}
