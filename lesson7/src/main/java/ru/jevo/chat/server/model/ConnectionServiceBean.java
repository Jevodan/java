package ru.jevo.chat.server.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.model.PacketError;
import ru.jevo.chat.model.PacketRegistry;
import ru.jevo.chat.model.PacketType;
import ru.jevo.chat.server.api.ConnectionService;
import ru.jevo.chat.server.service.Connection;

import javax.enterprise.context.ApplicationScoped;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ApplicationScoped
public class ConnectionServiceBean implements ConnectionService {

    @NotNull
    private final List<Connection> connections = new ArrayList<>();

    @NotNull
    public List<Connection> connections() {
        return connections;
    }


    @Nullable
    @Override
    public Connection get(@Nullable Socket socket) {
        if (socket == null) return null;
        for (final Connection connection : connections)
            if (connection.getSocket().equals(socket)) return connection;
        return null;
    }


    @Override
    public void add(final Socket socket) {
        if (socket == null) return;
        final Connection connection = new Connection(socket);
        connections.add(connection);
        System.out.println("Коннект клиента: " + connection.getId());
        //  System.out.println(Arrays.asList(connections).toString());
    }

    @Override
    public void remove(Socket socket) {
        if (socket == null) return;
        final Connection connection = get(socket);
        connections.remove(connection);
        System.out.println("пользователь:" + connection.getId() + "отсоединился");
    }

    @Override
    public void setLogin(@Nullable Socket socket, @Nullable String login) {
        if (!checkLogin(login)) return;
        @Nullable final Connection connection = get(socket);
        if (connection == null) return;
        connection.setLogin(login);
        System.out.println("Сессия..." + login);
    }

    @Override
    @SneakyThrows
    public void sendResult(@Nullable Socket socket, @Nullable Boolean success, String message) {
        final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        if (success) stream.writeUTF(message);
        else {
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final PacketError packetError = new PacketError();
            stream.writeUTF(objectMapper.writeValueAsString(packetError));
        }
    }

    @SneakyThrows
    @Override
    public void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message) {
        final DataOutputStream stream = new DataOutputStream(connection.getSocket().getOutputStream());
        stream.writeUTF(message);
    }

    @SneakyThrows
    @Override
    public void sendUsers(@Nullable Connection connection) {
        final DataOutputStream stream = new DataOutputStream(connection.getSocket().getOutputStream());
        stream.writeUTF(connection.getLogin() + "_PANEL");
    }

    @Override
    public void disconnect(@Nullable Socket socket) {

    }

    private boolean checkLogin(@Nullable String login) {
        return (login == null || login.isEmpty()) ? false : true;
    }
}
