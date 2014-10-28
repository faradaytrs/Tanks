package map.exceptions;

/**
 * Created by андрей on 21.10.2014.
 */
public class CellOccupiedException extends Exception{

    public final boolean isTank;

    public CellOccupiedException(boolean isTank){
        this.isTank = isTank;
    }



}
