package ru.jevo.server;

import lombok.Data;
import ru.jevo.api.ConnectionService;
import ru.jevo.server.model.Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ConnectionServiceBean implements ConnectionService {

    private final Server server;
    private final List<Connection> connections = new ArrayList<>();

    public ConnectionServiceBean(Server server) {
        this.server = server;
    }

    @Override
    public Connection get(Socket socket) {
        if (socket == null) return null;
        for (final Connection connection : connections)
            if (connection.getSocket().equals(socket)) return connection;
        return null;
    }

    @Override
    public void add(final Socket socket) {
        if (socket == null) return;
        final Connection connection = new Connection(server, socket);
        connections.add(connection);
        System.out.println("Коннект клиента: " + connection.getId());
        System.out.println(Arrays.asList(connections).toString());
    }

    @Override
    public void remove(Socket socket) {
        if (socket == null) return;
        final Connection connection = get(socket);
        connections.remove(connection);
        System.out.println("пользователь:" + connection.getId() + "отсоединился");
    }
}
