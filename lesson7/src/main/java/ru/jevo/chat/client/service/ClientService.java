package ru.jevo.chat.client.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.client.event.ClientMessageReadEvent;
import ru.jevo.chat.config.ChatConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@NoArgsConstructor
@ApplicationScoped
@Data
public class ClientService implements Client {

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private ChatConfig config;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    /**
     * Пишем в сокет, исходящий поток, на сервер
     * @param message
     */
    @Override
    @SneakyThrows
    public void send(String message) {
        out.writeUTF(message);
    }

    @Override
    @SneakyThrows
    public void run() {
        final String host = config.getHost();
        final Integer port = config.getPort();
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        clientMessageReadEvent.fireAsync(new ClientMessageReadEvent());
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }

    @Override
    @SneakyThrows
    public void exit() {
        socket.close();
        System.out.println("Откинулся...");
        System.exit(0);
    }
}
