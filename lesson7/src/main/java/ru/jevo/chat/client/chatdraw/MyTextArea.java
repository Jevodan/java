package ru.jevo.chat.client.chatdraw;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JTextArea {

    public MyTextArea(int a, int b) {
        super(a,b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/images/fon.png")).getImage(),0,0,null);

    }
}