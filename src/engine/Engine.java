package engine;

import gui.GUI;
import map.Map;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Engine{

	private GUI gui;
	private Map map;

	public Engine() {

		map = new Map(50, 50);

		gui = new GUI(map);

	}

	public void start() {

		boolean isGameUp = true;

		while (isGameUp) {

			gui.render();

		}

	}

}
