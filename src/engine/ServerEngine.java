package engine;

import gui.GUI;
import map.Location;
import map.Map;
import map.exceptions.CellOccupiedException;
import objects.Tank;
import socket.Server;

import java.awt.event.WindowEvent;

/**
 * Created by андрей on 07.12.2014.
 */
public class ServerEngine extends Engine {

    public ServerEngine() {
        map = new Map(20, 20);

        gui = new GUI(map);

        myTank = new Tank(new Location(5, 5));

        enemyTank = new Tank(new Location(10, 12));

        server = new Server();

        try {
            map.addElement(myTank);
            map.addElement(enemyTank);
        } catch (CellOccupiedException e) {
            if(e.isWall){
                map.deleteElement(myTank.getLocation());
                map.deleteElement(enemyTank.getLocation());
                try{
                    map.addElement(myTank);
                    map.addElement(enemyTank);
                }
                catch (CellOccupiedException e1){}
            }
        }

        server.sendMap(map);
        server.sendObject(enemyTank);
        server.sendData();
        server.sendObject(myTank);
        server.sendData();
        System.out.println("Build");
    }

    public void start() {
        boolean isGameUp = true;

        long time = 1;

        gui.render();

        while (isGameUp) {

            //delay
            System.out.println(1000 / (System.currentTimeMillis() - time + Double.MIN_NORMAL));
            time = System.currentTimeMillis();

            try {
                Thread.sleep(50);
                //System.out.println("now");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //change something on map
            moveDown(gui);
            moveLeft(gui);
            moveUp(gui);
            moveRight(gui);

            server.sendObject(myTank);
            myTank.setShooting(GUI.shooting);
            server.sendMyTankShooting(GUI.shooting);
            
            server.sendData();
            server.getData();
            
            updateObject(enemyTank, server);
            enemyTank.setShooting(server.isEnemyTankShooting());            

            for(int i = 0; i < bullets.size(); i ++)
                moveBullet(bullets.get(i));

            shot(myTank);
            shot(enemyTank);

            gui.render();

            if(!myTank.isAlive()){
                isGameUp = false;
                System.out.println("You lose!");
            }

            if(!enemyTank.isAlive()){
                isGameUp = false;
                System.out.println("You win!");
            }
        }
        gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
    }
}
