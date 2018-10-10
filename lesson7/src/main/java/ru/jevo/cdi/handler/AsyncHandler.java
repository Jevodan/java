package ru.jevo.cdi.handler;


import ru.jevo.cdi.event.AsyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

@ApplicationScoped
public class AsyncHandler {
    /**
     * Метод наблюдает событие на SyncEvent асинхронно
     * @param eventTest
     */
    public void handler(@ObservesAsync AsyncEvent eventTest){
        System.out.println(eventTest.getName());
        System.out.println("AsyncHandler: " + Thread.currentThread().getId());
    }
}
