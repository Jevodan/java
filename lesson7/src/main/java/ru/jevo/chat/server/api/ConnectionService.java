package ru.jevo.chat.server.api;



import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.model.PacketType;
import ru.jevo.chat.server.service.Connection;

import java.net.Socket;
import java.util.List;


public interface ConnectionService {

    @NotNull
    List<Connection> connections();

    @Nullable
    Connection get(final Socket socket);

    void add(@Nullable Socket socket);

    void remove(@Nullable Socket socket);

    void setLogin(@Nullable Socket socket, @Nullable String login);

    void sendResult(@Nullable Socket socket, @Nullable Boolean success, String message);

    void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message);

    void sendUsers(@Nullable Connection connection);

    void disconnect(@Nullable Socket socket);

}
