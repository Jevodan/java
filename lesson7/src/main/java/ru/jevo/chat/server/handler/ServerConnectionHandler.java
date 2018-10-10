package ru.jevo.chat.server.handler;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.api.Server;
import ru.jevo.chat.server.event.ServerConnectionEvent;
import ru.jevo.chat.server.event.ServerMessageEvent;
import ru.jevo.chat.server.event.ServerMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerConnectionHandler {

    @Inject
    ConnectionService connectionService;

    @Inject
    private Server server;

    @Inject
    Event<ServerMessageReadEvent> serverMessageEvent;

    @Inject
    Event<ServerConnectionEvent> serverConnectionEvent;

    @SneakyThrows
    public void handler(@Observes ServerConnectionEvent event){
        System.out.println("Сервер подключен...");
        final Socket socket = server.getServerSocket().accept();
        connectionService.add(socket);
        serverMessageEvent.fireAsync(new ServerMessageReadEvent(socket));
        serverConnectionEvent.fire(new ServerConnectionEvent());



    }

}
