package main;

import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Snake extends JLabel {
	public static int snakeMaxLength = -1;
	public static int snakeLength = 1;
	public static Field head;
	public static Field newHead;
	public static int speed = 100;
	static Color bodyColor = Color.decode("#00ff00");
	static ArrayList<Field> body = new ArrayList<Field>();

	public static void update() {// updates snakes location
		if (testCrash()) {
			Sound.play("Lose");
			return;
		}
		boolean hasFood = testFood();
		if (head == newHead || newHead == null) {
			return;
		}
		if (head != null) {
			head.setBackground(null);
		}
		Field last = null;
		if (body.size() > 1) {
			last = body.get(body.size() - 1);
			Field temp = body.get(0);
			for (int i = 0; i < body.size(); i++) {
				if (i + 1 < body.size()) {
					Field first = temp;
					temp = body.get(i + 1);
					body.set(i + 1, first);
				}
				body.get(i).setBackground(bodyColor);
				body.get(i).type = "BODY";
			}
		}
		if (body.size() > 0) {
			if (body.size() == 1) {
				last = body.get(0);
			}
			body.set(0, head);
			body.get(0).setBackground(bodyColor);
			body.get(0).type = "BODY";
		}
		head = newHead;
		head.setBackground(SnakeHead.color);
		if (hasFood) {
			body.add(last);
			return;
		}
		if (body.size() > 0 && !hasFood && last != null) {
			last.setBackground(null);
			last.type = "NONE";
		}

	}

	private static boolean testCrash() {// if has gone into itself
		if (newHead.type.equals("BODY")) {
			Main.end("<html><div style='text-align: center;'>" + "You Crashed into yourself!<br/>" + "Be cearful!<br/>"
					+ "Maybe you are clicking to fast?<br/>" + "Your Score is " + Snake.snakeLength + "</div></html>");
			return true;
		}
		return false;
	}

	private static boolean testFood() {// if eaten food
		if (newHead.type.equals("FOOD")) {
			Menu.scoreAdd(1);
			newHead.type = "NONE";
			Food.all.remove(newHead);
			Sound.play("BITE");
			return true;
		}
		return false;
	}
}
