package ru.jevo.api;

import ru.jevo.config.ChatConfig;

import java.util.concurrent.ExecutorService;

public interface ChatSide extends Runnable {

    ExecutorService getExecuter();

    ChatConfig getConfig();

    @Override
    void run();
}
