package map;

import map.exceptions.NotSwappableObjectException;
import map.exceptions.OutOfBorderException;
import map.exceptions.CellOccupiedException;
import objects.IGameObject;

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

		return field;

	}

	public void swapCells(int i1, int j1, int i2, int j2) throws OutOfBorderException, NotSwappableObjectException {

		if (i2 >= width || i2 < 0 || j2 >= height || j2 < 0) {
			throw new OutOfBorderException();
		}

		if (field[i2][j2] != Cell.SPACE) {
			throw new NotSwappableObjectException();
		}

		Cell buffer;

		buffer = field[i1][j1];
		field[i1][j1] = field[i2][j2];
		field[i2][j2] = buffer;

	}

    public void addElement( IGameObject obj ) throws CellOccupiedException{
        int x = obj.getLocation().getX();
        int y = obj.getLocation().getY();
        if( x >= 0 && x < width && y >= 0 && y < height) {
            if (field[x][y] == Cell.SPACE || field[x][y] == Cell.BULLET) {
                field[x][y] = obj.getType();
            } else if(field[x][y] == Cell.TANK){
                    throw new CellOccupiedException(true);
                } else {
                    throw new CellOccupiedException(false);
                }
        }
    }

    public void deleteElement(Location loc){
        int x = loc.getX();
        int y = loc.getY();
        if( x >= 0 && x < width && y >= 0 && y < height) {
            field[x][y] = Cell.SPACE;
        }
    }



}
