package ru.jevo.server;

import lombok.Data;
import ru.jevo.api.ChatSide;
import ru.jevo.config.ChatConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class ChatServer implements ChatSide {

    private final ExecutorService executer;

    private final ChatConfig config;

    private final Server server;


    public ChatServer() {
        config = new ChatConfig();
        executer = Executors.newFixedThreadPool(config.getThreads());
        server = new ChatServerStarter(config, executer);
    }

    @Override
    public void run() {
        server.run();
    }

    public static void main(String[] args) {
        final ChatServer server = new ChatServer();
        server.run();
    }

}



