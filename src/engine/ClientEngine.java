package engine;

import gui.GUI;
import objects.Tank;
import socket.Client;

import java.awt.event.WindowEvent;

/**
 * Created by андрей on 07.12.2014.
 */
public class ClientEngine extends Engine {

    public ClientEngine(){
        client = new Client();
        map = client.getMap();
        gui = new GUI(map);
        myTank = new Tank();
        enemyTank = new Tank();
        client.getObject(myTank);
        client.getObject(enemyTank);
        System.out.println("Build");
    }

    public void start(){

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
            updateObject(enemyTank, client);
            client.sendObject(myTank);

            for(int i = 0; i < bullets.size(); i ++)
                moveBullet(bullets.get(i));

            myTank.setShooting(GUI.shooting);
            client.sendMyTankShooting(GUI.shooting);
            enemyTank.setShooting(client.isEnemyTankShooting());
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
