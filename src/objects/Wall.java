package objects;

import map.Cell;
import map.Location;

/**
 * Created by андрей on 25.10.2014.
 */
public class Wall implements IGameObject {

    private Location location;

    private final Direction direction = Direction.NO_DIRECTION;

    public Wall(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Cell getType() {
        return Cell.WALL;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
