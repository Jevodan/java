package ru.jevo.chat.server.api;

import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.api.ChatSide;

import java.net.ServerSocket;

public interface Server extends ChatSide {

    /**
     * Сокет, на котором открыт сервак
     * @return
     */
    @NotNull
    ServerSocket getServerSocket();

    /**
     * Запуск сервера
     */
    void run();

}
