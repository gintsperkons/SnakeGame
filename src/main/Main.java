package main;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Main {
	public static final boolean MusicOn = false;
	static int windowX = 200;
	static int windowY = 50;
	static int windowWidth = 816;
	static int windowHeight = 900;
	static int gridSize = 800;
	static int fps = 120;
	static String windowName = "Snake Game";
	static String path = "Snake Game";

	public static ArrayList<ArrayList<Field>> grid;
	static Container cont;
	static JFrame frame;
	static boolean isPaused = false;
	static boolean gameOn = false;
	static boolean startGame = true;
	static MyListener ml1 = new MyListener();
	public static boolean sfxOn = true;
	public static boolean musicOn = true;

	public static void main(String[] args) {
		try {
			path = new File(".").getCanonicalPath();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Sound.load();
		Sound.play("MUSIC");
		frame = createWindow();
		frame.setVisible(true);
		cont = frame.getContentPane();
		Menu.create();
		grid = Grid.create(gridSize, cont);
		JLabel b = new JLabel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		b.setBounds(0, 0, 800, 800);
		b.setBorder(blackline);
		b.setBackground(Color.white);
		frame.addKeyListener(ml1);
		cont.add(b);
		Menu.buttonPlay.setEnabled(true);
		Menu.endScreen.setText("<html><div style='text-align: center;'>"
				+"Controls"
				+ "<br/>You are allowed to move through walls."
				+ "<br/>You are not allowed to run into yourself."
				+ "<br/>You need to collect food and grow to maximum size."
				+ "<br/>Ready"
				+  "</div></html>");
		play();

	}

	public static JFrame createWindow() {
		frame = new JFrame(windowName);
		frame.setBounds(windowX, windowY, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		return frame;
	}

	public static void play() {// main loop
		while (true) {
			if (Snake.snakeMaxLength <= Snake.snakeLength) {
				end("<html><div style='text-align: center;'>You win!" + "<br/>Your Score is " + Snake.snakeLength
						+ "</div></html>");
				Sound.play("WIN");
				break;
			}
			frame.repaint();
			if (gameOn && startGame) {
				startGame = false;
				Menu.endScreen.setVisible(false);
				game();
			}
			SharedCode.pause(1000 / fps);
		}

	}

	public static void game() {// game start 
		frame.repaint();
		MovementThread.make();
		SnakeHead.create();
		FoodThread.make();

	}

	public static void stop() {// game sto 
		Menu.endScreen.setVisible(true);
		Main.gameOn = false;
		Main.isPaused = false;
		Menu.buttonStop.setEnabled(false);
		Menu.buttonPause.setEnabled(false);
		Main.startGame = true;
		Food.all.clear();
		Snake.body.clear();
		SnakeHead.head = null;
		Snake.snakeLength = 1;
		Menu.scoreAdd(0);
		Menu.buttonPlay.setEnabled(true);
		for (int i = 0; i < Grid.grid.size(); i++) {
			for (int j = 0; j < Grid.grid.get(i).size(); j++) {

				Grid.grid.get(i).get(j).type = "NONE";
				Grid.grid.get(i).get(j).setBackground(null);
			}
		}
		frame.repaint();
	}

	public static void end(String reason) {// end screen
		stop();
		Menu.endScreen.setText(reason);
		Menu.endScreen.setVisible(true);
	}

}
