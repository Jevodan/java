package ru.jevo.client.task;

import ru.jevo.client.Client;

import java.util.Scanner;

public class ClientTaskMessageInput extends AbstractClientTask {

    public static final String CMD_EXIT = "exit";

    public ClientTaskMessageInput(Client client) {
        super(client);
    }


    @Override
    public void run() {
        System.out.println("Введите сообщение(Выход - exit): ");
        final Scanner in = new Scanner(System.in);
        final String message = in.nextLine();

        if (CMD_EXIT.equals(message)) {
            client.exit();
            return;
        }
        client.broadcast(message);
        System.out.println();
        client.run(new ClientTaskMessageInput(client));
    }
}
