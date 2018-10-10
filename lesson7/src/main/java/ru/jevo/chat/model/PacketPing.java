package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketPing extends Packet {
    public PacketPing() {super.setType(PacketType.PING); }

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.PING);
    }

}
