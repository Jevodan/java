package ru.jevo.server.task;

import ru.jevo.server.Server;

import java.net.Socket;

public class AbstractServerTask extends Thread {


    protected final Server server;

    public AbstractServerTask(Server server) {
        this.server =server;
    }


}
