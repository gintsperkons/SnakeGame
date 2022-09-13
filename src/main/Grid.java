package main;

import java.util.ArrayList;
import java.awt.Container;

public class Grid {// makes playing grid
	public static ArrayList<ArrayList<Field>> grid = new ArrayList<ArrayList<Field>>();
	public static ArrayList<ArrayList<Field>> create(int size, Container cont) {
		Snake.snakeMaxLength = (size/10)*(size/10);
		for (int i = 0; i < size/10; i++) {
			grid.add(new ArrayList<Field>());
			for (int j = 0; j < size/10; j++) {
				grid.get(i).add(Field.create(i,j, cont));
			}
		}
		return grid;
	}
}
