package ru.jevo;


import ru.jevo.api.ChatSide;
import ru.jevo.client.ChatClient;
import ru.jevo.server.ChatServer;

public class App {

    public static final String SERVER = "server";

    public static void main(String[] args) {
        getApp(args).run();
    }

    // выбираем что запускать сервер или клиент, с аргументом "server"
    private static ChatSide getApp(String[] args) {
        if (args == null || args.length == 0) return new ChatClient();
        if (args.length == 1 && SERVER.equals(args[0])) return new ChatServer();
        return new ChatClient();
    }

}
