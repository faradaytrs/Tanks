package objects;

import map.*;

/**
 * Created by андрей on 21.10.2014.
 */
public class Tank implements IGameObject {

    private Location location;

    private Look look;

    public Tank(Location location){

        setLocation(location);
        look = Look.UP;
    }


    public Look getLook() {
        return look;
    }

    public void setLook(Look look) {
        this.look = look;
    }



    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public Cell getType(){
        return Cell.TANK;
    }


}
