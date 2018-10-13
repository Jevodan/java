package ru.jevo.chat.client.chatdraw;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.jevo.chat.api.ChatSide;
import ru.jevo.chat.client.api.ConfigureSwing;
import ru.jevo.chat.client.chatdraw.events.ButtonEvents;
import ru.jevo.chat.client.event.ClientChatInputEvent;
import ru.jevo.chat.client.user.UserClient;
import ru.jevo.chat.model.Packet;
import ru.jevo.chat.model.PacketType;


import javax.enterprise.event.Event;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static ru.jevo.chat.client.chatdraw.ConfigSwing.*;

@ApplicationScoped
@NoArgsConstructor
@Getter
public class ChatDraw implements ChatSide {

    @Inject
    ConfigureSwing conf;

    @Inject
    ButtonEvents buttonEvents;

    private String name = "Аноним";
    private JButton buttonBroadcast = new JButton();
    private JButton buttonPing = new JButton();
    private JButton buttonRegistry = new JButton();
    private JButton buttonLogin = new JButton();
    private JButton buttonPrivat = new JButton();
    private JButton buttonLogout = new JButton();
    private JTextArea labelMain;
    private JTextArea labelUsers = new JTextArea();
    private JTextField fieldMessage = new JTextField();
    private JPanel panelTOP = new JPanel();
    private JPanel panelMiddle = new JPanel();
    private JPanel panelBottom = new JPanel();
    private JPanel panelRegistry = new JPanel();
    private JPanel panelLoginSuccess = new JPanel();
    private JPanel chatPanel = new JPanel();
    private JLabel fon;
    private JScrollPane scrollPane;
    private JFrame frame;
    private JTextField fieldLogin = new JTextField();
    private JTextField fieldPassword = new JTextField();
    private JLabel labelLogin = new JLabel("Логин");
    private JLabel labelPassword = new JLabel("Пароль");
    private JLabel labelLoginSuccess = new JLabel();


    @Override
    public void run() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("чатец");
        conf.buttonConfig(buttonBroadcast, MESSAGE, SEND, PacketType.BROADCAST);
        conf.buttonConfig(buttonRegistry, REG, REGISTRATION, PacketType.REGISTRY);
        conf.buttonConfig(buttonPing, PING, PINGG, PacketType.PING);
        conf.buttonConfig(buttonLogin, LOGIN, ENTER, PacketType.LOGIN);
        conf.buttonConfig(buttonPrivat, PRIVAT, PRIVATE, PacketType.PRIVAT);
        conf.buttonConfig(buttonLogout, EXIT, OUT, PacketType.LOGOUT);

        frame.setSize(1000, 1040);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        labelMain = new JTextArea(22, 31);
        labelMain.setEditable(false);
        messagePossible(false);

        scrollPane = new JScrollPane(labelMain);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        conf.elementConfig(labelMain, 650, 70000);
        conf.elementDopConfig(labelMain, "Окно чата");
        conf.elementConfig(labelUsers, 300, 735);
        conf.elementDopConfig(labelUsers, "Пользователи");
        conf.elementConfig(fieldMessage, 640, 55);
        conf.panelConfig(panelTOP, 950, 750);
        conf.panelConfig(panelMiddle, 950, 70);
        conf.elementDopConfig(fieldMessage, "Введите сообщение");
        conf.panelConfig(panelBottom, 950, 70);
        conf.panelConfig(chatPanel, 950, 750);
        conf.elementConfig(fieldLogin, 350, 60);
        conf.elementConfig(fieldPassword, 350, 60);
        conf.elementConfig(labelLoginSuccess, 500, 60);

        panelLoginSuccess.add(labelLoginSuccess);
        panelRegistry.add(labelLogin);
        panelRegistry.add(fieldLogin);
        panelRegistry.add(labelPassword);
        panelRegistry.add(fieldPassword);
        panelTOP.add(scrollPane);
        panelTOP.add(labelUsers);
        panelMiddle.add(fieldMessage);
        panelMiddle.add(buttonBroadcast);
        panelBottom.add(buttonPing);
        panelBottom.add(buttonRegistry);
        panelBottom.add(buttonLogin);
        panelBottom.add(buttonPrivat);
        panelBottom.add(buttonLogout);
        chatPanel.add(panelLoginSuccess);
        chatPanel.add(panelRegistry);
        chatPanel.add(panelTOP);
        chatPanel.add(panelMiddle);
        chatPanel.add(panelBottom);
        frame.getContentPane().add(chatPanel);


        buttonBroadcast.addActionListener(event -> clickMessage());
        buttonPing.addActionListener(event -> clickPing());
        buttonLogin.addActionListener(event -> clickLogin());
        buttonLogout.addActionListener(event ->System.exit(0));
        buttonRegistry.addActionListener(event -> clickRegistry());

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    clickMessage();
                }
            }
        });
        frame.setVisible(true);
    }

    private void clickRegistry() {
        @NotNull String login = fieldLogin.getText();
        @NotNull String password = fieldPassword.getText();
        UserClient.login = login;
        UserClient.password = password;
        buttonEvents.clickRegistry();
    }

    private void clickLogin() {
        @NotNull String login = fieldLogin.getText();
        @NotNull String password = fieldPassword.getText();
        UserClient.login = login;
        UserClient.password = password;
        buttonEvents.clickLogin();
    }

    private void clickPing() {
        buttonEvents.clickPing();
    }

    public String getName() {
        return name;
    }

    public void messagePossible(boolean flag) {


        fieldMessage.setEditable(flag);
        buttonBroadcast.setEnabled(flag);
        panelRegistry.setVisible(!flag);
        panelLoginSuccess.setVisible(flag);
        buttonLogin.setEnabled(!flag);
        buttonRegistry.setEnabled(!flag);
        labelLoginSuccess.setText("Вы залогигены: " + UserClient.login);
        labelLoginSuccess.setBackground(Color.LIGHT_GRAY);
    }

    private void clickMessage() {
       // addTextToChat(getFieldMessage().getText(), UserClient.login);
        buttonEvents.clickMessage(getFieldMessage().getText());
        getFieldMessage().setText("");
    }

    public void addTextToChat(String message, String login) {
        String nameWrite = "";
        if (!login.isEmpty()) nameWrite = login + " пишет: ";
        labelMain.append("\n" + nameWrite + message);
    }

    public void addUserPanel(String message){
        labelUsers.append("\n" + message);
    }
}
