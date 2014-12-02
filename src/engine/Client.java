package engine;

import map.Map;

import java.io.*;
import java.net.*;

/**
 * Created by андрей on 02.12.2014.
 */
public class Client {

    private Socket socket;
    private InetAddress ip;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static final String ipAddress = "127.0.0.1";
    public static final int port = 5000;

    public Client() {
        try {
            this.ip = InetAddress.getByName(ipAddress);
            socket = new Socket(ip, port);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
