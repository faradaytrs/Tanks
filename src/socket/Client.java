package socket;

import map.Location;
import map.Map;
import objects.Direction;
import objects.IGameObject;

import java.io.*;
import java.net.*;

/**
 * Created by андрей on 02.12.2014.
 */
public class Client extends SocketConnection {


    private InetAddress ip;

    public static final String ipAddress = "25.2.250.58";
    public static final int port = 5000;

    public Client() {
        try {
            this.ip = InetAddress.getByName(ipAddress);
            socket = new Socket(ip, port);
            System.out.println("Connected");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputBuffer = new byte[4];
            inputBuffer = new byte[4];
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map getMap(){
        byte[] numbers = {};
        int width = Map.width, height = Map.height;
        try {
            width = inputStream.readInt();
            height = inputStream.readInt();
            numbers = new byte[width * height];
            inputStream.readFully(numbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Map(width, height, numbers);
    }


}
