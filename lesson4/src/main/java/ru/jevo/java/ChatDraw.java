package ru.jevo.java;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatDraw extends JFrame {
    private String name;
    private JButton button = new JButton("Отправить");
    private JTextArea labelMain = new JTextArea("");
    private JLabel labelUsers = new JLabel("");
    private JTextField fieldMessage = new JTextField("", 45);

    @Override
    public String getName() {
        return name;
    }

    public ChatDraw(String name) throws HeadlessException {
        super("Чат!");

        this.name = name;
        this.setSize(1000, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Font font = new Font("Verdana", Font.PLAIN, 16);
        JPanel panel = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelLeft = new JPanel();
        Border centralBorder = BorderFactory.createTitledBorder("Окно чата");
        Border eastBorder = BorderFactory.createTitledBorder("Пользователи");







        labelMain.setPreferredSize(new Dimension(630, 803));
        labelMain.setBorder(centralBorder);
        labelMain.setBackground(Color.white);
        //   labelMain.setVerticalAlignment(JLabel.TOP);
        labelMain.setFont(font);
        labelMain.setOpaque(true);
        labelMain.setEditable(false);



        labelUsers.setVerticalAlignment(JLabel.TOP);
        labelUsers.setPreferredSize(new Dimension(220, 850));
        labelUsers.setText(name);
        labelUsers.setBorder(eastBorder);
        labelUsers.setBackground(Color.GRAY);
        labelUsers.setFont(font);



        panelSouth.setBackground(Color.LIGHT_GRAY);
        panelSouth.setLayout(new FlowLayout());
        panelSouth.add(fieldMessage);
        panelSouth.add(button);
        panelSouth.setPreferredSize(new Dimension(630, 35));

        panelLeft.setLayout(new FlowLayout());
        panelLeft.add(labelMain);
        panelLeft.add(panelSouth);
        panelLeft.setPreferredSize(new Dimension(630, 850));

        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(850, 850));
        panel.add(panelLeft);
        panel.add(labelUsers);

        this.getContentPane().add(panel);

        button.addActionListener(event -> getMessageIntoChat());

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
