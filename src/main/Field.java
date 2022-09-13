package main;

import javax.swing.JLabel;
import java.awt.Container;

@SuppressWarnings("serial")
public class Field extends JLabel {// each box in grid
	private int posX = -1;
	private int posY = -1;
	private int size = 8;
	public String type = "NONE";

	private Field(int tX, int tY,Container cont) {
		posX = tX;
		posY = tY;
		this.setBounds(posX, posY, size,size);
		this.setOpaque(true);
		cont.add(this);
	}

	public static Field create(int numX, int numY, Container cont) {
		int tX = (numX * 10) + 1;
		int tY = (numY * 10) + 1;
		Field f = new Field(tX,tY, cont);
		return f;
	}
}
