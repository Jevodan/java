package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketRegistry extends Packet {

    public PacketRegistry() {super.setType(PacketType.REGISTRY); }

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.REGISTRY);
    }
}
