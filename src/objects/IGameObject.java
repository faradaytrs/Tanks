package objects;

import map.*;

/**
 * Created by андрей on 21.10.2014.
 */
public interface IGameObject {

    public Location getLocation();
    public void setLocation(Location location);
    public Cell getType();

}
