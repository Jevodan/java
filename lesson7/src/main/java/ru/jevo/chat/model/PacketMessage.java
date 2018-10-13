package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketMessage extends Packet {

    private String login;

    private String message;

    public PacketMessage() {super.setType(PacketType.MESSAGE); }

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.MESSAGE);
    }
}
