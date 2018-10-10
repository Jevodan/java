package ru.jevo;

import ru.jevo.chat.api.ChatSide;
import ru.jevo.chat.client.chatdraw.AppChat;
import ru.jevo.chat.client.service.ClientService;
import ru.jevo.chat.server.model.ServerServiceBean;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class App {

    public static final String SERVER = "server";

    public static void main(String[] args) {
        getApp(args).run();
    }

    private static ChatSide getApp(String[] args) {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize();
    //    if (args == null || args.length == 0) return CDI.current().select(ClientService.class).get();
   //     if (args.length == 1 && SERVER.equals(args[0])) return CDI.current().select(ServerServiceBean.class).get();
       // return CDI.current().select(ClientService.class).get();
        return CDI.current().select(AppChat.class).get();
    }
}
