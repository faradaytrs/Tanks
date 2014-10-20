package map;

import map.exceptions.NotSwappableObjectException;
import map.exceptions.OutOfBorderException;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Map {

	private Cell[][] field;

	public static int width;
	public static int height;

	public Map(int width, int height) {

		this.width = width;
		this.height = height;

		this.field = initMap(width, height);

	}

	public Cell[][] getField() {
		return field;
	}

	private Cell[][] initMap(int width, int height) {

		Cell[][] field = new Cell[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				field[i][j] = Cell.SPACE;
			}
		}

		field[5][5] = Cell.TANK;

		return field;

	}

	public void swapCells(int i1, int j1, int i2, int j2) throws OutOfBorderException, NotSwappableObjectException {

		if (i2 >= width || i2 < 0 || j2 >= height || j2 < 0) {
			throw new OutOfBorderException();
		}

		if (field[i2][j2] == Cell.TANK || field[i2][j2] == Cell.WALL) {
			throw new NotSwappableObjectException();
		}

		Cell buffer;

		buffer = field[i1][j1];
		field[i1][j1] = field[i2][j2];
		field[i2][j2] = buffer;

	}


}
