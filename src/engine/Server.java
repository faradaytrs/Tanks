package engine;

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
            socket = serverSocket.accept();
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
