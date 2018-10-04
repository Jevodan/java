package ru.jevo.client;

import lombok.Data;
import lombok.SneakyThrows;
import ru.jevo.api.ChatSide;
import ru.jevo.client.task.AbstractClientTask;
import ru.jevo.client.task.ClientTaskMessageInput;
import ru.jevo.client.task.ClientTaskMessageRead;
import ru.jevo.client.task.ClientTaskMessageSend;
import ru.jevo.config.ChatConfig;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Data
public class ChatClientStarter implements ChatSide, Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final ChatConfig config;
    private final ExecutorService executer;

    public ChatClientStarter(final ChatConfig config, final ExecutorService executer) {
        this.config = config;
        this.executer = executer;
    }


    @Override
    @SneakyThrows
    public void run() {
        final String host = config.getHost();
        final Integer port = config.getPort();
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        run(new ClientTaskMessageRead(this));
        run(new ClientTaskMessageInput(this));


    }

    @Override
    public void run(AbstractClientTask task) {
        if (task == null) return;
        executer.submit(task);
    }

    @Override
    public void exit() {
        System.out.println("Клиент откинулся");
        System.exit(0);
    }

    @Override
    public void broadcast(String message) {
        if (message == null || message.isEmpty()) return;
        run(new ClientTaskMessageSend(this, message));
    }


}
