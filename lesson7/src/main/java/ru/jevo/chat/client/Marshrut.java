package ru.jevo.chat.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.client.chatdraw.ChatDraw;
import ru.jevo.chat.client.event.ClientLoginEvent;
import ru.jevo.chat.client.user.UserClient;
import ru.jevo.chat.model.Packet;
import ru.jevo.chat.model.PacketBroadcast;
import ru.jevo.chat.model.PacketLogin;
import ru.jevo.chat.server.api.UserService;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class Marshrut {

    @Inject
    ChatDraw draw;

    @Inject
    private Event<ClientLoginEvent> clientLoginEvent;


    @SneakyThrows
    public void outMessage(Packet packet){
        switch (packet.getType()){
            case PING :
                draw.addTextToChat(packet.getType().name(), "");
                break;
            case LOGIN :
                draw.messagePossible(true);
                draw.addTextToChat(packet.getType().name(), "");
                break;
            case BROADCAST :
                draw.addTextToChat(packet.getMessage(), packet.getLogin());
                break;
            case REGISTRY :
                draw.addTextToChat(packet.getType().name(), "");
                break;
            case LOGOUT :
                break;
            default:
                draw.addTextToChat(packet.getMessage(),"");
                break;
        }
    }
}
