package ru.jevo.chat.client.api;

import ru.jevo.chat.api.ChatSide;

import javax.swing.*;

public interface ConfigureSwing  {

    void  elementConfig(JComponent component, int width, int height);

    void  elementDopConfig(JComponent component, String border);

    void panelConfig(JPanel panel, int width, int height);

    void buttonConfig(JButton button, String icon, String name);

}
