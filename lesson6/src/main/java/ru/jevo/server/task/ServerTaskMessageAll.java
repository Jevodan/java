package ru.jevo.server.task;

import ru.jevo.server.model.Connection;
import ru.jevo.server.Server;

public class ServerTaskMessageAll extends AbstractServerTask {

    protected final String message;
    public ServerTaskMessageAll(Server server, String message) {
        super(server);
        this.message = message;
    }

    @Override
    public void run() {
        for (final Connection connection: server.connections())
            connection.send(message);
    }
}
