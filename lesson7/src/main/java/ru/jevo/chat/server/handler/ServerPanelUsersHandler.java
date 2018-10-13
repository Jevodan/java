package ru.jevo.chat.server.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.event.ServerPanelUsersEvent;
import ru.jevo.chat.server.service.Connection;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

public class ServerPanelUsersHandler {

    @Inject
    private ConnectionService connectionService;

    public void handler(@ObservesAsync ServerPanelUsersEvent event){
        System.out.println("ServerPanelUsersHandler");
        for (final Connection conn: connectionService.connections()) {
            System.out.println(conn);
            connectionService.sendUsers(conn);
        }
    }
}
