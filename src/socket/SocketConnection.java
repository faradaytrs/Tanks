package socket;

import map.Location;
import objects.Direction;
import objects.IGameObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by андрей on 07.12.2014.
 */
public class SocketConnection {

    protected Socket socket;
    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;
    protected byte[] outputBuffer;
    protected byte[] inputBuffer;

    public void sendObject(IGameObject obj){
        outputBuffer[0] = (byte) obj.getLocation().getX();
        outputBuffer[1] = (byte) obj.getLocation().getY();
        outputBuffer[2] = (byte) obj.getDirection().ordinal();

    }

    public void getObject(IGameObject obj){
        int x = inputBuffer[0];
        int y = inputBuffer[1];
        obj.setDirection((Direction.values()[inputBuffer[2]]));
        obj.setLocation(new Location(x, y));
    }

    public boolean isEnemyTankShooting() {
        return (inputBuffer[3] == 1) ? true : false;
    }

    public void sendMyTankShooting(boolean flag){
        outputBuffer[3] = (byte) (flag ? 1 : 0);
    }

    public void sendData(){
        try{
            outputStream.write(outputBuffer);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getData(){
        try{
            inputStream.readFully(inputBuffer);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
