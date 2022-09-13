package main;

import java.awt.Color;

@SuppressWarnings("serial")
public class SnakeHead extends Snake{
	static public Color color = Color.decode("#008800");
 public static void create(){
	 Main.grid.get(39).get(39).setBackground(Color.decode("#008800"));
	 Snake.head = Main.grid.get(39).get(39);
	 Snake.head.type = "HEAD";
 }
	

}

