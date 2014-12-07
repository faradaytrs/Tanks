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

    public void sendObject(IGameObject obj){
        try{
            outputStream.writeInt(obj.getLocation().getX());
            outputStream.writeInt(obj.getLocation().getY());
            outputStream.writeInt(obj.getDirection().ordinal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getObject(IGameObject obj){
        try{
            int x = inputStream.readInt();
            int y = inputStream.readInt();
            obj.setLocation(new Location(x, y));
            obj.setDirection(Direction.values()[inputStream.readInt()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEnemyTankShooting() {
        try{
            return inputStream.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendMyTankShooting(boolean flag){
        try{
            outputStream.writeBoolean(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
