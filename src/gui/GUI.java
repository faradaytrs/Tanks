package gui;

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
	public static final int width = 500;
	public static final int height = 500;

	private Map map;

	public GUI(Map map) {

		this.map = map;

		setSize(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(TITLE);
		setResizable(false);
		setVisible(true);

	}

	public void paint(Graphics g) {

		renderMap(map, g);

		g.fillRect(50, 50, 50, 50);

	}

	public void render() {
		repaint();
	}

	public void renderMap(Map map, Graphics g) {



	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
