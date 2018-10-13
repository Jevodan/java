package ru.jevo.chat.client.handler;

import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.chatdraw.ChatDraw;
import ru.jevo.chat.client.event.*;
import ru.jevo.chat.client.user.UserClient;
import ru.jevo.chat.server.event.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientChatInputHandler {

    @Inject
    ChatDraw draw;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientBroadcastEvent> clientBroadcastEvent;

    @Inject
    private Event<ClientPingEvent> clientPingEvent;

    @Inject
    private Event<ClientLoginEvent> clientLoginEvent;

    @Inject
    private Event<ClientRegistryEvent> clientRegistryEvent;

    @Inject
    private Event<ClientChatInputEvent> clientChatInputEvent;


    public void handler(@Observes ClientChatInputEvent event){
        draw.run();
        draw.getFrame().setVisible(true);
/*
        switch (event.message) {
            case "REG":
                clientRegistryEvent.fire(new ClientRegistryEvent());
                break;
            case "LOGIN":
                clientLoginEvent.fire(new ClientLoginEvent());
                break;
            case "PING":
                clientPingEvent.fireAsync(new ClientPingEvent());
              //  clientMessageInputEvent.fire(new ClientMessageInputEvent());
                break;
            case "BROAD":
                clientBroadcastEvent.fire(new ClientBroadcastEvent(event.message));
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                break;
        }
*/
    }

}
