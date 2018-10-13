package ru.jevo.chat.client.event;

import lombok.Data;

@Data
public class ClientBroadcastEvent {

    String message;

    public ClientBroadcastEvent(String message) {
        this.message = message;

    }
}
