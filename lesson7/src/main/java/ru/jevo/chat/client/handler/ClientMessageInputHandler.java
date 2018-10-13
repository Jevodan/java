package ru.jevo.chat.client.handler;


import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.api.Client;
import ru.jevo.chat.client.event.*;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageInputHandler {

    @NotNull
    public static final String REG = "REGISTRY";

    @NotNull
    public static final String LOGIN = "LOGIN";

    @NotNull
    public static final String PING = "PING";

    @NotNull
    public static final String BROAD = "BROADCAST";

    @NotNull
    public static final String EXIT = "EXIT";

    @Inject
    private Client client;

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

    public void handler(@Observes final ClientMessageInputEvent event) {
        System.out.println("!!!!!!!!!!");
        /*
        System.out.println("(" + REG + ", " + LOGIN + ", " + PING + ", " + BROAD + ", " + EXIT + ") Введите сообщение :");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();

        switch (message) {
            case REG:
                clientRegistryEvent.fire(new ClientRegistryEvent());
                break;
            case LOGIN:
                clientLoginEvent.fire(new ClientLoginEvent());
                break;
            case PING:
                clientPingEvent.fireAsync(new ClientPingEvent());
                clientMessageInputEvent.fire(new ClientMessageInputEvent());
                break;
            case BROAD:
                clientBroadcastEvent.fire(new ClientBroadcastEvent(message));
                break;
            case EXIT:
                client.exit();
                break;
            default:
                break;
        }
        System.out.println();
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
*/
    }
}
