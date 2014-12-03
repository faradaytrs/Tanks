package engine;

import gui.GUI;
import map.Map;

/**
 * Created by андрей on 03.12.2014.
 */
public class ClientEngine {

    private GUI gui;
    private Map map;
    private Client client;

    public ClientEngine(){
        client = new Client();
    }


    public void start(){
        map = client.getMap();
        gui = new GUI(map);
        gui.render();
        while(true){

        }
    }
}
