package ru.jevo.cdi;

import ru.jevo.cdi.service.EventService;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class AppSOA {
    public static void main(String[] args) {
        System.out.println("ThreadId: " + Thread.currentThread().getId());
        SeContainerInitializer.newInstance().addPackages(AppSOA.class).initialize();

        final EventService eventService = CDI.current().select(EventService.class).get();
        eventService.run();
    }
}
