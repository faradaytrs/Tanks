package map.exceptions;
import map.Cell;

/**
 * Created by андрей on 21.10.2014.
 */
public class CellOccupiedException extends Exception{

    public final boolean isTank;
    public final boolean isWall;

    public CellOccupiedException(Cell errorObj) {
        if(errorObj == Cell.TANK){
            isTank = true;
            isWall = false;
            return;
        }
        if(errorObj == Cell.WALL){
            isWall = true;
            isTank = false;
            return;
        }
        isTank = false;
        isWall = false;
    }

    public CellOccupiedException(){
        isTank = false;
        isWall = false;
    }


}
