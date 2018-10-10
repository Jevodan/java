package ru.jevo.chat.server.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.event.ServerBroadcastEvent;
import ru.jevo.chat.server.service.Connection;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

public class ServerBroadcastHandler {

    @Inject
    private ConnectionService connectionService;

    public void handler(@ObservesAsync ServerBroadcastEvent event){
        System.out.println("ServerMessageBroadcastHandler");
        @NotNull final Socket socket = event.getSocket();
        @Nullable final Connection connection = connectionService.get(socket);
        if(connection == null) return;
        @Nullable final String login = connection.getLogin();
        if(login == null || login.isEmpty()) return;
        @NotNull final String message = event.getMessageJSON();
        for (final Connection conn: connectionService.connections()) {
            System.out.println(conn);
            connectionService.sendMessage(conn, login, message);
        }
    }
}
