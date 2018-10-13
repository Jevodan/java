package ru.jevo.cdi.service;


import ru.jevo.cdi.event.AsyncEvent;
import ru.jevo.cdi.event.SyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class EventService {

    @Inject
    private Event<AsyncEvent> asyncEvent;

    @Inject
    private Event<SyncEvent> syncEvent;

    public void run(){
        asyncEvent.fireAsync(new AsyncEvent("рыцарь"));
        syncEvent.fire(new SyncEvent());
    }
}
