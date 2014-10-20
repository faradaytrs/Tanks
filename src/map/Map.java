package map;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Map {

	private Cell[][] field;

	public Map(int width, int height) {

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

		return field;

	}


}
