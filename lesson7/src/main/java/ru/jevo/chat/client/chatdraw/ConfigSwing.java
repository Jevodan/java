package ru.jevo.chat.client.chatdraw;

import lombok.NoArgsConstructor;
import ru.jevo.chat.client.api.ConfigureSwing;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.*;
import java.awt.*;

@NoArgsConstructor
@ApplicationScoped
public class ConfigSwing implements ConfigureSwing {

    public static final int BUTTON_WIDTH = 185;
    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_WIDTH_LARGE = 295;
    Font font = new Font("TimesRoman", Font.PLAIN, 24);
    Font fontButton = new Font("TimesRoman", Font.BOLD, 20);

    @Override
    public void elementConfig(JComponent component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
        component.setBackground(Color.white);
        component.setFont(font);
        component.setOpaque(true);
    }

    @Override
    public void elementDopConfig(JComponent component, String nameBorder) {
        component.setBorder(BorderFactory.createTitledBorder(nameBorder));
    }

    @Override
    public void panelConfig(JPanel panel, int width, int height) {
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void buttonConfig(JButton button, String icon, String name) {
        button.setIcon(new ImageIcon(this.getClass().getResource("/images/" + icon + ".png")));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        if (name.equals("Отправить"))
            button.setPreferredSize(new Dimension(BUTTON_WIDTH_LARGE, BUTTON_HEIGHT));
        button.setText("  " + name);
        button.setFont(fontButton);
        button.setBackground(Color.WHITE);
     //   button.setMargin(new Insets(5,5,10,10));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));



    }

    public void lol(){
        System.out.println("уууууууууу");
    }


}
