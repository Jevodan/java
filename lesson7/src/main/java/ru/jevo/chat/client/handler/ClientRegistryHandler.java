package ru.jevo.chat.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.client.event.ClientRegistryEvent;
import ru.jevo.chat.model.PacketRegistry;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientRegistryHandler {

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@Observes final ClientRegistryEvent event) {

        @NotNull final Scanner in = new Scanner(System.in);
        System.out.println("Ваш логин");
        @NotNull final String login = in.nextLine();

        System.out.println("Ваш пароль");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketRegistry packetRegistry = new PacketRegistry();
        packetRegistry.setLogin(login);
        packetRegistry.setPassword(password);

        client.send(objectMapper.writeValueAsString(packetRegistry));
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }


}
