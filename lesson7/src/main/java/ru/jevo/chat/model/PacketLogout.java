package ru.jevo.chat.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PacketLogout extends Packet {

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.LOGOUT);
    }
}
