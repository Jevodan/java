package ru.jevo.chat.server.handler;

import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.model.PacketType;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.event.ServerPingEvent;
import ru.jevo.chat.server.service.Connection;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

public class ServerPingHandler {

    @Inject
    private ConnectionService connectionService;

    public void handler(@ObservesAsync ServerPingEvent event){

        System.out.println("PINGHandler");
        final Connection connection = connectionService.get(event.getSocket());
        if (connection == null) return;
        System.out.println("Пингуется коннект айди: " + connection.getId());
        @NotNull final String message = event.getMessageJSON();
        connectionService.sendResult(event.getSocket(), true, message);
    }
}
