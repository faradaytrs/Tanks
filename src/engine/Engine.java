package engine;

import gui.GUI;
import map.Location;
import map.Map;
import map.exceptions.CellOccupiedException;
import map.exceptions.NotSwappableObjectException;
import map.exceptions.OutOfBorderException;
import objects.Bullet;
import objects.Direction;
import objects.IGameObject;
import objects.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Engine {

	private GUI gui;
	private Map map;
    private Tank myTank;
    private List<Bullet> bullets = new ArrayList<Bullet>();

	public Engine() {

		map = new Map(20, 20);

		gui = new GUI(map);

        myTank = new Tank(new Location(5, 5));


        try {
            map.addElement(myTank);
        } catch (CellOccupiedException e) {
            //e.printStackTrace();
        }

    }

	public void start() {

		boolean isGameUp = true;

		long time = 1;

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
            shot(myTank);
            for(Bullet bullet: bullets)
                moveBullet(bullet);
			gui.render();

		}

	}

    private void shot(Tank tank){
        if(gui.shooting){
            Bullet bullet = new Bullet(checkDirection(tank), tank.getDirection());
            bullets.add(bullet);
            try {
                map.addElement(bullet);
            } catch (CellOccupiedException e) {
                e.printStackTrace();
            }
        }
        gui.shooting = false;

    }

    private Location checkDirection(IGameObject obj){
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


    private void moveBullet(Bullet bullet){

        int x = checkDirection(bullet).getX();
        int y = checkDirection(bullet).getY();

        map.deleteElement(bullet.getLocation());

        bullet.setLocation(new Location(x, y));
        try {
            map.addElement(bullet);
        } catch (CellOccupiedException e) {
            e.printStackTrace();

        }
    }

	private void moveLeft(GUI gui) {

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

	private void moveRight(GUI gui) {

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

	private void moveUp(GUI gui) {

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

	private void moveDown(GUI gui) {

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
