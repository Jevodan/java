package ru.jevo.client;

import lombok.Data;
import ru.jevo.api.ChatSide;
import ru.jevo.config.ChatConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class ChatClient implements ChatSide {

    private final ChatConfig config;
    private final ExecutorService executer;
    private Client client;

    public ChatClient() {
        config = new ChatConfig();
        executer = Executors.newCachedThreadPool();
        client = new ChatClientStarter(config, executer);
    }
    @Override
    public void run() {
        client.run();
    }

    public static void main(String[] args) {
        final ChatClient client = new ChatClient();
        client.run();
    }
}
