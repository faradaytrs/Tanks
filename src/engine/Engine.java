package engine;

import gui.GUI;
import main_menu.Menu;
import map.Location;
import map.Map;
import map.exceptions.CellOccupiedException;
import map.exceptions.NotSwappableObjectException;
import map.exceptions.OutOfBorderException;
import objects.*;
import socket.Client;
import socket.Server;
import socket.SocketConnection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public abstract class Engine {

	protected GUI gui;
    protected Map map;
    protected Tank myTank, enemyTank;
    protected List<Bullet> bullets = new ArrayList<>();
    protected Server server;
    protected Client client;
    protected Menu menuFrame;

    protected void updateObject(IGameObject obj, SocketConnection socket){
        map.deleteElement(obj.getLocation());
        socket.getObject(obj);
        try {
            map.addElement(obj);
        } catch (CellOccupiedException e) {
            if( obj instanceof Bullet) {
                bullets.remove((Bullet) obj);
                if(e.isTank){
                    map.deleteElement(obj.getLocation());

                }
            }
        }
    }

    protected void shot(Tank tank){
        if(tank.isShooting()){
            Bullet bullet = new Bullet(checkDirection(tank), tank.getDirection(), tank.equals(myTank));
            bullets.add(bullet);
            try {
                map.addElement(bullet);
            } catch (CellOccupiedException e) {
                bullets.remove(bullet);
                if(e.isTank){
                    map.deleteElement(bullet.getLocation());
                    if(bullet.isMine){ enemyTank.destroyed(); }
                    else{ myTank.destroyed(); }
                }
            }
            tank.setShooting(false);
            GUI.shooting = false;
        }


    }

    protected Location checkDirection(IGameObject obj){
        int dx = 0, dy = 0;
        switch(obj.getDirection()){
            case UP:
                dy = 1;
                break;
            case DOWN:
                dy = -1;
                break;
            case RIGHT:
                dx = 1;
                break;
            case LEFT:
                dx = -1;
                break;
        }
        return new Location(obj.getLocation().getX() + dx, obj.getLocation().getY() + dy);
    }


    protected void moveBullet(Bullet bullet){

        int x = checkDirection(bullet).getX();
        int y = checkDirection(bullet).getY();

        map.deleteElement(bullet.getLocation());

        bullet.setLocation(new Location(x, y));
        try {
            map.addElement(bullet);
        } catch (CellOccupiedException e) {
            bullets.remove(bullet);
            if(e.isTank){
                map.deleteElement(bullet.getLocation());
                if(bullet.isMine){ enemyTank.destroyed(); }
                else{ myTank.destroyed(); }
            }
            //e.printStackTrace();

        }
    }

	protected void moveLeft(GUI gui) {

		if (!gui.movingLeft) return;

        if(myTank.getDirection() != Direction.LEFT) {

            myTank.setDirection(Direction.LEFT);
            gui.movingLeft = false;
            return;
        }

		//todo add check if cell on border

		try {


            Location tankLocation = myTank.getLocation();

            map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX() - 1, tankLocation.getY());

            myTank.setLocation(new Location(tankLocation.getX() - 1, tankLocation.getY()));


		} catch (OutOfBorderException e) {
			//e.printStackTrace();
		} catch (NotSwappableObjectException e) {
			//e.printStackTrace();
		} finally {
			gui.movingLeft = false;
		}

	}

	protected void moveRight(GUI gui) {

		if (!gui.movingRight) return;

        if(myTank.getDirection() != Direction.RIGHT) {

            myTank.setDirection(Direction.RIGHT);
            gui.movingRight = false;
            return;
        }

		//todo add check if cell on border

		try {
                Location tankLocation = myTank.getLocation();

                map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX() + 1, tankLocation.getY());

                myTank.setLocation(new Location(tankLocation.getX() + 1, tankLocation.getY()));

		} catch (OutOfBorderException e) {
			//e.printStackTrace();
		} catch (NotSwappableObjectException e) {
			//e.printStackTrace();
		} finally {
			gui.movingRight = false;
		}

	}

	protected void moveUp(GUI gui) {

		if (!gui.movingUp) return;

        if(myTank.getDirection() != Direction.UP) {

            myTank.setDirection(Direction.UP);
            gui.movingUp = false;
            return;
        }


		//todo add check if cell on border

		try {

            Location tankLocation = myTank.getLocation();
            map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX(), tankLocation.getY() + 1);

            myTank.setLocation(new Location(tankLocation.getX(), tankLocation.getY() + 1));

		} catch (OutOfBorderException e) {
			//e.printStackTrace();
		} catch (NotSwappableObjectException e) {
			//e.printStackTrace();
		} finally {
			gui.movingUp = false;
		}


	}

	protected void moveDown(GUI gui) {

		if (!gui.movingDown) return;

        if(myTank.getDirection() != Direction.DOWN) {

            myTank.setDirection(Direction.DOWN);
            gui.movingDown = false;
            return;
        }

		//todo add check if cell on border

		try {

            Location tankLocation = myTank.getLocation();

			map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX(), tankLocation.getY() - 1);

            myTank.setLocation( new Location(tankLocation.getX(), tankLocation.getY() - 1));

		} catch (OutOfBorderException e) {
			//e.printStackTrace();
		} catch (NotSwappableObjectException e) {
			//e.printStackTrace();
		} finally {
			gui.movingDown = false;
		}

	}

}
