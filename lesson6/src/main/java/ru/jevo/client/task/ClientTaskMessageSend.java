package ru.jevo.client.task;


import ru.jevo.client.Client;

public class ClientTaskMessageSend extends AbstractClientTask {

    private String message;

    public ClientTaskMessageSend(Client client, String message){
        super(client);
        this.message = message;
    }

    @Override
    public void run(){

        try {
            client.getOut().writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
            client.exit();
        }
    }
}
