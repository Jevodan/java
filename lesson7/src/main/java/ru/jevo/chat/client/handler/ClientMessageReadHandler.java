package ru.jevo.chat.client.handler;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.client.event.ClientMessageReadEvent;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.InputStream;


public class ClientMessageReadHandler {

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @SneakyThrows
    public void handler(@ObservesAsync ClientMessageReadEvent event) {
        System.out.println(client.getIn().readUTF());
        clientMessageReadEvent.fireAsync(new ClientMessageReadEvent());
    }
}
