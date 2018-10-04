package ru.jevo.server;

import lombok.Data;
import lombok.SneakyThrows;
import ru.jevo.api.ChatSide;
import ru.jevo.api.ConnectionService;
import ru.jevo.config.ChatConfig;
import ru.jevo.server.model.Connection;
import ru.jevo.server.task.AbstractServerTask;
import ru.jevo.server.task.ServerTaskConnection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Data
public class ChatServerStarter implements Server, ChatSide {

    private final ChatConfig config;
    private final ExecutorService executer;
    private final ConnectionService connectionService;
    private ServerSocket serverSocket;

    public ChatServerStarter(final ChatConfig config, final ExecutorService executer) {
        this.connectionService = new ConnectionServiceBean(this);
        this.config = config;
        this.executer = executer;
    }

    @Override
    public List<Connection> connections() {
        return connectionService.getConnections();
    }

    @Override
    public void add(Socket socket) {
        connectionService.add(socket);

    }

    @Override
    public void remove(Socket socket) {
        connectionService.remove(socket);
    }

    @Override
    @SneakyThrows
    public void run() {
        serverSocket = new ServerSocket(config.getPort());
        run(new ServerTaskConnection(this));
    }


    @Override
    public void run(AbstractServerTask task) {
        executer.submit(task);
    }
}
