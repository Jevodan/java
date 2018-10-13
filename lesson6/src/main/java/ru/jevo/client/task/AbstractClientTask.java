package ru.jevo.client.task;

import ru.jevo.client.Client;

public class AbstractClientTask extends Thread {

    protected final Client client;

    public AbstractClientTask(Client client) {
        this.client = client;
    }
}
