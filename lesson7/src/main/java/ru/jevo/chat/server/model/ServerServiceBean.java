package ru.jevo.chat.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.config.ChatConfig;
import ru.jevo.chat.server.api.Server;
import ru.jevo.chat.server.event.ServerConnectionEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.net.ServerSocket;

@Getter
@NoArgsConstructor
@ApplicationScoped
public class ServerServiceBean implements Server {

    @Inject
    private ChatConfig config;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    @NotNull
    private ServerSocket serverSocket;


    /**
     * Запускаем сервер
     */
    @Override
    @SneakyThrows
    public void run() {
        serverSocket = new ServerSocket(config.getPort());
        serverConnectionEvent.fire(new ServerConnectionEvent());

    }
}
