package ru.jevo.cdi.event;

import lombok.Data;



@Data
public class AsyncEvent {

    private String name;

    public AsyncEvent(String name) {
        this.name = name;
    }
}
