package engine;

import gui.GUI;
import map.Map;
import map.exceptions.OutOfBorderException;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Engine {

	private GUI gui;
	private Map map;

	Location tankLocation = new Location(5, 5);

	public Engine() {

		map = new Map(20, 20);

		gui = new GUI(map);

	}

	public void start() {

		boolean isGameUp = true;

		while (isGameUp) {

			//delay

			try {
				Thread.sleep(10);
				//System.out.println("now");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//change something on map
			moveDown(gui);
			moveLeft(gui);
			moveUp(gui);
			moveRight(gui);

			gui.render();

		}

	}

	private void moveLeft(GUI gui) {

		if (!gui.movingLeft) return;

		//todo add check if cell on border

		try {

			map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX() - 1, tankLocation.getY());

			tankLocation = new Location(tankLocation.getX() - 1, tankLocation.getY());

		} catch (OutOfBorderException e) {
			e.printStackTrace();
		} finally {
			gui.movingLeft = false;
		}

	}

	private void moveRight(GUI gui) {

		if (!gui.movingRight) return;

		//todo add check if cell on border

		try {

			map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX() + 1, tankLocation.getY());

			tankLocation = new Location(tankLocation.getX() + 1, tankLocation.getY());

		} catch (OutOfBorderException e) {
			e.printStackTrace();
		} finally {
			gui.movingRight = false;
		}

	}

	private void moveUp(GUI gui) {

		if (!gui.movingUp) return;

		//todo add check if cell on border

		try {
			map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX(), tankLocation.getY() + 1);

			tankLocation = new Location(tankLocation.getX(), tankLocation.getY() + 1);
		} catch (OutOfBorderException e) {
			e.printStackTrace();
		} finally {
			gui.movingUp = false;
		}


	}

	private void moveDown(GUI gui) {

		if (!gui.movingDown) return;

		//todo add check if cell on border

		try {

			map.swapCells(tankLocation.getX(), tankLocation.getY(), tankLocation.getX(), tankLocation.getY() - 1);

			tankLocation = new Location(tankLocation.getX(), tankLocation.getY() - 1);

		} catch (OutOfBorderException e) {
			e.printStackTrace();
		} finally {
			gui.movingDown = false;
		}

	}

}
