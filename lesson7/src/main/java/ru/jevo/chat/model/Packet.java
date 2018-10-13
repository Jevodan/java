package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Packet {
    private String id = UUID.randomUUID().toString();
    private PacketType type = PacketType.NONE;
    private String message = "";

    private String login = "";

    @Nullable
    private String password = "";
}
