package ru.jevo.chat.client.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.ClientLoginEvent;
import ru.jevo.chat.client.event.ClientMessageInputEvent;
import ru.jevo.chat.client.user.UserClient;
import ru.jevo.chat.model.PacketLogin;
import ru.jevo.chat.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientLoginHandler {

    @Inject
    Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@Observes final ClientLoginEvent event) {

        /**
         * захватываем полдьзовательский ввод
         */

        /*
        @NotNull final Scanner in = new Scanner(System.in);
        System.out.println("Введите логин");
        @NotNull final String login = in.nextLine();

        System.out.println("Введите пароль");
        @NotNull final String password = in.nextLine();
        */

        @NotNull final String login = UserClient.login;
        @NotNull final String password = UserClient.password;

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packetLogin = new PacketLogin();
        packetLogin.setLogin(login);
        packetLogin.setPassword(password);

        client.send(objectMapper.writeValueAsString(packetLogin));
     //   clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }


}
