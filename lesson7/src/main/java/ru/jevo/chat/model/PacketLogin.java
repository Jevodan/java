package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketLogin extends Packet {

    @Nullable
    private String login;

    @Nullable
    private String password;

    public PacketLogin() {super.setType(PacketType.LOGIN);}

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.LOGIN);
    }
}
