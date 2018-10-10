package ru.jevo.chat.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientBroadcastEvent;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.model.PacketBroadcast;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

public class ClientBroadcastHandler {

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@Observes final ClientBroadcastEvent event) {

        @NotNull final Scanner in = new Scanner(System.in);
        System.out.println("Введите сообщение");
        @NotNull final String message = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketBroadcast packetBroadcast = new PacketBroadcast();
        packetBroadcast.setMessage(message);


        client.send(objectMapper.writeValueAsString(packetBroadcast));
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }
}
