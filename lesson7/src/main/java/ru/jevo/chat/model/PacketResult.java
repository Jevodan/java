package ru.jevo.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketResult extends Packet {

    public PacketResult() { super.setType(PacketType.REGISTRY); }

    private Boolean success = true;

    public PacketResult(Boolean success) {
        this.success = success;
    }

    @Override
    public void setType(PacketType type) {
        super.setType(PacketType.RESULT);
    }
}
