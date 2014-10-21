package objects;

import map.Cell;
import map.Location;

/**
 * Created by андрей on 21.10.2014.
 */
public class Bullet implements IGameObject{

    private Location location;

    private Direction direction;

    public Bullet(Location location, Direction direction) {
        this.location = location;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
        return Cell.BULLET;
    }


}
