package ru.jevo.api;

import ru.jevo.server.model.Connection;

import java.net.Socket;
import java.util.List;

public interface ConnectionService {

   // List<Connection> connections = null;
    Connection get(final Socket socket);

    public List<Connection> getConnections();
    void add(final Socket socket);
    void remove(final Socket socket);
}
