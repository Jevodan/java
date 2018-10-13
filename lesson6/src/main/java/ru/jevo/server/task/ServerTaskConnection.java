package ru.jevo.server.task;

import lombok.SneakyThrows;
import ru.jevo.server.Server;

import java.net.Socket;

public class ServerTaskConnection extends AbstractServerTask {

    public ServerTaskConnection(final Server server) {
        super(server);
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Сервер ожидает подключений...");
        final Socket socket = server.getServerSocket().accept();
        server.run(new ServerTaskConnection(server));
        server.run(new ServerTaskMessageRead(server, socket));
        server.add(socket);
    }
}
