package ru.jevo.client.task;

import ru.jevo.client.Client;

public class ClientTaskMessageRead extends AbstractClientTask {

    public ClientTaskMessageRead(final Client client) {
        super(client);
    }
    @Override
    public void run(){

        try {
            final String message = client.getIn().readUTF();
            System.out.println("- " + message + " -");
            client.run(new ClientTaskMessageRead(client));
        } catch (Exception e) {
            e.printStackTrace();
            client.exit();
        }
    }
}
