package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketError extends Packet {

    private String login = "unknown";

    private String message = "ОШИБКА";

    public PacketError() {super.setType(PacketType.ERROR); }

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.ERROR);
    }
}
