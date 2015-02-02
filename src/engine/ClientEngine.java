package engine;

import gui.GUI;
import main_menu.Menu;
import objects.Tank;
import socket.Client;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Created by андрей on 07.12.2014.
 */
public class ClientEngine extends Engine {

    public ClientEngine(Menu menuFrame,String ipAddress){
        this.menuFrame = menuFrame;
        client = new Client(ipAddress);
        map = client.getMap();
        gui = new GUI(map);
        myTank = new Tank();
        enemyTank = new Tank();
        client.getData();
        client.getObject(myTank);
        client.getData();
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

            client.sendObject(myTank);
            myTank.setShooting(GUI.shooting);
            client.sendMyTankShooting(GUI.shooting);

            client.getData();
            client.sendData();

            updateObject(enemyTank, client);
            enemyTank.setShooting(client.isEnemyTankShooting());

            for(int i = 0; i < bullets.size(); i ++)
                moveBullet(bullets.get(i));

            shot(myTank);
            shot(enemyTank);

            gui.render();

            if(!myTank.isAlive()){
                isGameUp = false;
                gui.printLoseMessage();
            }

            if(!enemyTank.isAlive()){
                isGameUp = false;
                gui.printWinMessage();
            }

        }
        client.closeConnection();
        gui.dispose();
        menuFrame.makeFrameVisible();
    }
}
