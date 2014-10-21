package map;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Location {

	public int getX() {
		return X;
	}

	private void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	private void setY(int y) {
		this.Y = y;
	}

	private int X;
	private int Y;


	public Location(int x, int y) {
		setX(x);
		setY(y);
	}

}
