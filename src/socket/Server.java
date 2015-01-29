package socket;

import map.Cell;
import map.Location;
import map.Map;
import objects.Direction;
import objects.IGameObject;

import java.net.*;
import java.io.*;

/**
 * Created by андрей on 02.12.2014.
 */
public class Server extends SocketConnection {

    private ServerSocket serverSocket;

    public static final int port = 5000;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server created");
            socket = serverSocket.accept();
            System.out.println("Connection");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputBuffer = new byte[4];
            inputBuffer = new byte[4];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMap(Map map) {
        byte[] numbers = map.getNumbers();
        try {
            outputStream.writeInt(Map.width);
            outputStream.writeInt(Map.height);
            outputStream.write(numbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
