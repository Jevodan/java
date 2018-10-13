package ru.jevo.chat.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.model.PacketRegistry;
import ru.jevo.chat.model.PacketType;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.api.UserService;
import ru.jevo.chat.server.event.ServerPanelUsersEvent;
import ru.jevo.chat.server.event.ServerRegistryEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerRegistryHandler {

    @Inject
    private UserService userService;

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerPanelUsersEvent> serverPanelUsersEvent;

    @SneakyThrows
    public void handler(@ObservesAsync ServerRegistryEvent event) {
        System.out.println("Регистрация");
        System.out.println(event.getMessageJSON());
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessageJSON();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketRegistry packetRegistry = objectMapper.readValue(message, PacketRegistry.class);
        final boolean result = userService.registry(packetRegistry.getLogin(), packetRegistry.getPassword());
        connectionService.sendResult(socket, result, message);
     //   serverPanelUsersEvent.fireAsync(new ServerPanelUsersEvent());

    }
}
