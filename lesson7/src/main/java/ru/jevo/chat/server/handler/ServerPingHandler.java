package ru.jevo.chat.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.model.PacketPing;
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
        connectionService.sendResult(event.getSocket(), true);

    }
}
