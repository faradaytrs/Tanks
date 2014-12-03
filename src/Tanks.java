import engine.ClientEngine;
import engine.Engine;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Tanks {

	public static void main(String[] args) {

		if(args[0].compareTo("server") == 0){

            Engine engine = new Engine();
            engine.start();
        }
        else {
            ClientEngine engine = new ClientEngine();
            engine.start();
        }

	}

}
