package ru.jevo.server.model;

import lombok.Data;
import lombok.SneakyThrows;
import ru.jevo.api.ConnectionService;
import ru.jevo.server.Server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

@Data
public class Connection {

    private Server server;
    private Socket socket;
    private String id = UUID.randomUUID().toString();

    @SneakyThrows
    public void send(String message) {
        final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF(message);
    }

    public Connection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

}
