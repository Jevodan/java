package ru.jevo.chat.client.chatdraw;

import lombok.NoArgsConstructor;
import ru.jevo.chat.api.ChatSide;
import ru.jevo.chat.client.api.ConfigureSwing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@ApplicationScoped
public class ChatDraw extends JFrame {

    @Inject
    ConfigureSwing conf;


    public static final String MESSAGE = "message";
    public static final String REG = "reg";
    public static final String PING = "ping";
    public static final String LOGIN = "login";
    public static final String PRIVAT = "privat";
    public static final String EXIT = "exit";
    public static final String SEND = "Отправить";
    public static final String REGISTRATION = "Регистрация";
    public static final String PINGG = "Пинг";
    public static final String ENTER = "Войти";
    public static final String PRIVATE = "Приват";
    public static final String OUT = "Выйти";
    public static final String CHAT = "Чат!";
    private String name;
    private JButton buttonBroadcast;
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
    private JPanel chatPanel = new JPanel();
    private JLabel fon;
    JScrollPane scrollPane;


    @Override
    public String getName() {
        return name;
    }


    public ChatDraw() throws HeadlessException {

        super(CHAT);


        conf.buttonConfig(buttonBroadcast, MESSAGE, SEND);
        conf.buttonConfig(buttonRegistry, REG, REGISTRATION);
        conf.buttonConfig(buttonPing, PING, PINGG);
        conf.buttonConfig(buttonLogin, LOGIN, ENTER);
        conf.buttonConfig(buttonPrivat, PRIVAT, PRIVATE);
        conf.buttonConfig(buttonLogout, EXIT, OUT);

        this.name = name;
        this.setSize(1000, 970);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        labelMain = new JTextArea(22, 31);
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

        panelTOP.add(scrollPane);
        panelTOP.add(labelUsers);
        panelMiddle.add(fieldMessage);
        panelMiddle.add(buttonBroadcast);
        panelBottom.add(buttonPing);
        panelBottom.add(buttonRegistry);
        panelBottom.add(buttonLogin);
        panelBottom.add(buttonPrivat);
        panelBottom.add(buttonLogout);
        labelMain.setEditable(false);
        chatPanel.add(panelTOP);
        chatPanel.add(panelMiddle);
        chatPanel.add(panelBottom);
        this.getContentPane().add(chatPanel);
        this.setVisible(true);


        buttonBroadcast.addActionListener(event -> getMessageIntoChat());

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getMessageIntoChat();
                }
            }
        });

    }

    public void getMessageIntoChat() {
        labelMain.setText(labelMain.getText() + "\n" + getName() + " пишет: " + fieldMessage.getText());
        fieldMessage.setText("");
    }

}
