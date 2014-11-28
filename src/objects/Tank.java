package objects;

import map.*;

/**
 * Created by андрей on 21.10.2014.
 */
public class Tank implements IGameObject {

    private Location location;

    private Direction direction;

    public Tank(Location location){

        setLocation(location);
        direction = Direction.UP;
    }

    @Override
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
    public Cell getType(){
        return Cell.TANK;
    }


}
