package objects;

import map.*;

/**
 * Created by андрей on 21.10.2014.
 */
public class Tank implements IGameObject {

    private Location location;
    private boolean shooting;
    private Direction direction;
    private boolean isAlive = true;

    public Tank(){
        location = new Location(0,0);
        direction = Direction.NO_DIRECTION;
        shooting = false;
    }

    public Tank(Location location){
        shooting = false;
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

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean isAlive(){ return isAlive; }

    public void destroyed(){ isAlive = false; }
}
