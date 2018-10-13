package ru.jevo.server;

import ru.jevo.server.model.Connection;
import ru.jevo.server.task.AbstractServerTask;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public interface Server extends Runnable {

    ServerSocket getServerSocket();

    List<Connection> connections();

    void run(AbstractServerTask task);

    void add(Socket socket);

    void remove(Socket socket);
}
