package ru.jevo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.jevo.cdi.PacketLoginTest;

public class MappingTest {
    @Test
    @SneakyThrows
    public void testToSSON(){
        final PacketLoginTest packetLogin = new PacketLoginTest();
        packetLogin.setLogin("jevo");
        packetLogin.setPassword("jevo");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(packetLogin);

        System.out.println(json);
        Assert.assertNotNull(json);
    }

    @Test
    @SneakyThrows
    public void testFromSSON(){
        final String json = "{\"login\": \"123\", \"password\": \"456\" }";
        final ObjectMapper objectMapper = new ObjectMapper();

        final PacketLoginTest packetLogin = objectMapper.readValue(json, PacketLoginTest.class);
        System.out.println(packetLogin.getLogin());
        Assert.assertNotNull(packetLogin);
        Assert.assertNotNull(packetLogin.getLogin());
        Assert.assertNotNull(packetLogin.getPassword());

    }

}
