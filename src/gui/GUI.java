package gui;

import map.Cell;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Andrey Izotov on 20.10.2014.
 */
public class GUI extends JFrame implements KeyListener {

	public static final String TITLE = "Tanks";
	public static final int width = 1000;
	public static final int height = 1000;

	public static final int CELL_SIZE = 30;

	public static boolean movingLeft = false;
	public static boolean movingRight = false;
	public static boolean movingUp = false;
	public static boolean movingDown = false;
    public static boolean shooting = false;

	private Map map;

	public GUI(Map map) {

		this.map = map;

		setSize(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(TITLE);
		setResizable(false);
		setVisible(true);

		addKeyListener(this);

	}

	public void paint(Graphics g) {

		renderMap(map, g);

	}

	public void render() {
		repaint();
	}

	public void renderMap(Map map, Graphics g) {

		Cell[][] field = map.getField();

		int width = field.length;
		int height = field[0].length;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {



				switch(field[i][j]) {

					case SPACE:
						g.setColor(new Color(0xCAD1B3));
						break;
					case TANK:
						g.setColor(new Color(0x1C8321));
						break;
					case WALL:
						g.setColor(new Color(0xA1782E));
                    case BULLET:
                        g.setColor(new Color(0x4B35DA));

				}

				g.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);

			}
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
        //System.out.println(code);

		switch (code) {

			case 65:
				movingLeft = true;
				break;
			case 87:
				movingDown = true;
				break;
			case 68:
				movingRight = true;
				break;
			case 83:
				movingUp = true;
                break;

            //Shooting on SPACE

            case 32:
                shooting = true;
                break;


		}


	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
