package main_menu;

import engine.ClientEngine;
import engine.ServerEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Андрей on 31.01.2015.
 */
public class Menu extends JFrame implements ActionListener {

    private JPanel contentPane;
    private static final String PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    public Menu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 175);
        setTitle("Tanks game");
        setLocation(500, 200);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));


        JButton connectBtn = createButton("Connect to...");
        connectBtn.setActionCommand("connect");
        JButton serverBtn = createButton("Create server");
        serverBtn.setActionCommand("create server");
        JButton aboutBtn = createButton("About");
        aboutBtn.setActionCommand("about");
        JButton exitBtn = createButton("Exit");
        exitBtn.setActionCommand("exit");

        setContentPane(contentPane);
        setVisible(true);


    }

    private JButton createButton(String name){
        JButton btn = new JButton(name);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        contentPane.add(btn);
        btn.addActionListener(this);
        return btn;
    }

    private void createServer(){
        setVisible(false);
        Menu menuFrame = this;
        Thread t = new Thread()
        {
            public void run() {
                ServerEngine engine = new ServerEngine(menuFrame);
                engine.start();
            }
        };
        t.start();
    }

    private void connect() {
        String ipAddress = JOptionPane.showInputDialog(this, "Type desirable IP address here:");
        Menu menuFrame = this;
        setVisible(false);
        if(validate(ipAddress)){
            Thread t = new Thread()
            {
                public void run() {
                    ClientEngine engine = new ClientEngine(menuFrame, ipAddress);
                    engine.start();
                }
            };
            t.start();
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect IP address!");
        }
    }

    public void makeFrameVisible(){
        setVisible(true);
    }

    public static boolean validate(final String ip){
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "connect":
                connect();
                break;
            case "create server":
                createServer();
                break;
//            case "about": about(); break;
            case "exit":
                dispose();
                break;
        }
    }
}
