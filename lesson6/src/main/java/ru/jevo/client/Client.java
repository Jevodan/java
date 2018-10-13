package ru.jevo.client;

import ru.jevo.client.task.AbstractClientTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Client {

    public void run();

    public void run(AbstractClientTask task);

    void exit();

    void broadcast(String message);

    DataInputStream getIn();

    DataOutputStream getOut();
}
