package ru.jevo.server.task;

import ru.jevo.server.Server;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerTaskMessageRead extends AbstractServerTask {

    protected Socket socket;

    public ServerTaskMessageRead(Server server, Socket socket) {
        super(server);
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            System.out.println("Входящее сообщение)");
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final String message = in.readUTF();
            System.out.println(message);
            server.run(new ServerTaskMessageRead(server, socket));
            server.run(new ServerTaskMessageAll(server, message));
        } catch (Exception e) {
           server.remove(socket);
        }

    }
}
