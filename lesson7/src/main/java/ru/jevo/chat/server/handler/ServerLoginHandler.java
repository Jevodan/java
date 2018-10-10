package ru.jevo.chat.server.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.model.PacketLogin;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.api.UserService;
import ru.jevo.chat.server.event.ServerLoginEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.IOException;
import java.net.Socket;

@ApplicationScoped
public class ServerLoginHandler {

    @Inject
    private UserService userService;

    @Inject
    private ConnectionService connectionService;

    @SneakyThrows
    public void handler(@ObservesAsync final ServerLoginEvent event) {
        System.out.println("ServeLOGINHandler");
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String messageJSON = event.getMessageJSON();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packetLogin = objectMapper.readValue(messageJSON, PacketLogin.class);
        boolean check = userService.check(packetLogin.getLogin(), packetLogin.getPassword());
        if (check) {
            connectionService.setLogin(socket, packetLogin.getLogin());
            System.out.println("Подконнекчены");
        } else System.out.println("Неправильное имя / пароль" + packetLogin.getLogin());
        connectionService.sendResult(socket, check);

    }

}
