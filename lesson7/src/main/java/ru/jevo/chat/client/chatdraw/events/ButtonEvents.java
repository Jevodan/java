package ru.jevo.chat.client.chatdraw.events;

import ru.jevo.chat.client.chatdraw.ChatDraw;
import ru.jevo.chat.client.event.*;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class ButtonEvents implements ButtonEventsShow {

    @Inject
    private Event<ClientChatInputEvent> clientChatInputEvent;

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

    @Override
    public void clickPing() { clientPingEvent.fireAsync(new ClientPingEvent());}

    @Override
    public void clickMessage(String message) { clientBroadcastEvent.fireAsync(new ClientBroadcastEvent(message));}

    @Override
    public void clickLogin() { clientLoginEvent.fire(new ClientLoginEvent());}

    @Override
    public void clickRegistry() { clientRegistryEvent.fire(new ClientRegistryEvent());}

    @Override
    public void clickPrivat() {}

    @Override
    public void clickExit() {}
}
