package ru.jevo.chat.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.client.event.ClientMessageReadEvent;
import ru.jevo.chat.client.event.ClientPingEvent;
import ru.jevo.chat.model.PacketPing;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

public class ClientPingHandler {

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@ObservesAsync ClientPingEvent event) {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketPing packetPing = new PacketPing();
        client.send(objectMapper.writeValueAsString(packetPing));
      //  clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }
}
