package ru.jevo.chat.api;


public interface ChatSide extends Runnable {

    @Override
    void run();
}
