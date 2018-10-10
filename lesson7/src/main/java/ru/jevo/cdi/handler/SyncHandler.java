package ru.jevo.cdi.handler;


import ru.jevo.cdi.event.SyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class SyncHandler {

    /**
     * Метод наблюдает событие на SyncEvent синхронно
     * @param eventTest
     */
    public void handler(@Observes SyncEvent eventTest){
        System.out.println("SyncHandler: " + Thread.currentThread().getId());
    }
}
