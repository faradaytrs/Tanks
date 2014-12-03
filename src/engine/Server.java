package engine;

import map.Map;

import java.net.*;
import java.io.*;

/**
 * Created by андрей on 02.12.2014.
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static final int port = 5000;

    public Server(){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server created");
            socket = serverSocket.accept();
            System.out.println("Connection");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMap(Map map){
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
