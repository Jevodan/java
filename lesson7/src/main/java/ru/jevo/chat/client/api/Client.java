package ru.jevo.chat.client.api;

import ru.jevo.chat.api.ChatSide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public interface Client extends ChatSide {

    Socket getSocket();

    DataInputStream getIn();

    DataOutputStream getOut();

    void send(String message);

    void run();

    void exit();

}

