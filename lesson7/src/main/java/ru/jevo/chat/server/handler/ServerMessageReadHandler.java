package ru.jevo.chat.server.handler;

import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.event.ServerLoginEvent;
import ru.jevo.chat.server.event.ServerMessageInputEvent;
import ru.jevo.chat.server.event.ServerMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.InputStream;

@ApplicationScoped
public class ServerMessageReadHandler {

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerMessageReadEvent> serverMessageReadEvent;

    @Inject
    private Event<ServerMessageInputEvent> serverMessageInputEvent;

    public void handler(@ObservesAsync final ServerMessageReadEvent event) {
        System.out.println("ServerMessageReadhandler");
        try {
            @NotNull final InputStream inputStream = event.getSocket().getInputStream();
            @NotNull final DataInputStream in = new DataInputStream(inputStream);
            @NotNull final String messageJSON = in.readUTF();
            System.out.println("Прием" + messageJSON);
            serverMessageReadEvent.fireAsync(new ServerMessageReadEvent(event.getSocket()));
            serverMessageInputEvent.fireAsync(new ServerMessageInputEvent(event.getSocket(), messageJSON));
        } catch(@NotNull final Exception e) {
            System.out.println("вылет");
            connectionService.remove(event.getSocket());
        }
    }
}
