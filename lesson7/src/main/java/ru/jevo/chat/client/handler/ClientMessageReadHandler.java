package ru.jevo.chat.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.Marshrut;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.chatdraw.ChatDraw;
import ru.jevo.chat.client.event.ClientMessageReadEvent;
import ru.jevo.chat.model.Packet;
import ru.jevo.chat.model.PacketBroadcast;
import ru.jevo.chat.model.PacketType;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.IOException;

public class ClientMessageReadHandler {

    @Inject
    Marshrut marshrutizator;

    @Inject
    ChatDraw draw;

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @SneakyThrows
    public void handler(@ObservesAsync ClientMessageReadEvent event) {
        @NotNull final String response = client.getIn().readUTF();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Packet packet = objectMapper.readValue(response, Packet.class);
     //   System.out.println("ТИП:" + packet.getType().name());

        marshrutizator.outMessage(packet);
        //   System.out.println(client.getIn().readUTF());


       clientMessageReadEvent.fireAsync(new ClientMessageReadEvent());
    }
}
