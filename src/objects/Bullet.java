package objects;

import map.Cell;
import map.Location;

/**
 * Created by андрей on 21.10.2014.
 */
public class Bullet implements IGameObject{

    private Location location;
    public final boolean isMine;
    private Direction direction;

    public Bullet(){
        location = new Location(0,0);
        direction = Direction.NO_DIRECTION;
        isMine = false;
    }

    public Bullet(Location location, Direction direction, boolean flag) {
        this.location = location;
        this.direction = direction;
        isMine = flag;
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
