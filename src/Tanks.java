import engine.ClientEngine;
import engine.Engine;
import engine.ServerEngine;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class Tanks {

	public static void main(String[] args) {

        if(args[0].compareTo("server") == 0){
            ServerEngine serverEngine = new ServerEngine();
            serverEngine.start();
        }
        else{
            ClientEngine clientEngine = new ClientEngine();
            clientEngine.start();
        }

	}

}
