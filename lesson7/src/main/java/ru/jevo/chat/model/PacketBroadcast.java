package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketBroadcast extends Packet {

    public PacketBroadcast() {super.setType(PacketType.BROADCAST); }

    private String message = "";

    private String login = "";
}
