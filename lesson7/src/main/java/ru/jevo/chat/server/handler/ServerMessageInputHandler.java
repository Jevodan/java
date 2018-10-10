package ru.jevo.chat.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.model.Packet;
import ru.jevo.chat.server.api.Server;
import ru.jevo.chat.server.event.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.IOException;
import java.net.Socket;


@ApplicationScoped
public class ServerMessageInputHandler {

    @Inject
    Event<ServerPingEvent> serverPingEvent;

    @Inject
    Event<ServerLoginEvent> serverLoginEvent;

    @Inject
    Event<ServerLogoutEvent> serverLogoutEvent;

    @Inject
    Event<ServerRegistryEvent> serverRegistryEvent;

    @Inject
    Event<ServerBroadcastEvent> serverBroadcastEvent;

    @SneakyThrows
    public void handler(@ObservesAsync final ServerMessageInputEvent event) {
        System.out.println("ServerMessageInputHandler");
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String messageJSON = event.getMessageJSON();
        System.out.println(messageJSON);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Packet packet = objectMapper.readValue(messageJSON, Packet.class);
        System.out.println("ТИП:" + packet.getType().name());

        switch (packet.getType()) {
            case PING:
                serverPingEvent.fireAsync(new ServerPingEvent(socket, messageJSON));
                break;
            case LOGIN:
                serverLoginEvent.fireAsync(new ServerLoginEvent(socket, messageJSON));
                break;
            case LOGOUT:
                serverLogoutEvent.fireAsync(new ServerLogoutEvent(socket, messageJSON));
                break;
            case REGISTRY:
                serverRegistryEvent.fireAsync(new ServerRegistryEvent(socket, messageJSON));
                break;
            case BROADCAST:
                serverBroadcastEvent.fireAsync(new ServerBroadcastEvent(socket, messageJSON));
                break;
            default:
                System.out.println("ЛОЛ");
                break;
        }
    }
}
