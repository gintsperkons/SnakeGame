package main;

public class Movement {
	static String direction = "UP";

	static void move() {// moveing method
		if (Snake.head != null) {
			switch (direction) {
			case "UP":
				newHead(Snake.head.getX() / 10, Snake.head.getY() / 10 - 1);
				Snake.update();
				break;
			case "DOWN":
				newHead(Snake.head.getX() / 10, Snake.head.getY() / 10 + 1);
				Snake.update();

				break;
			case "LEFT":
				newHead(Snake.head.getX() / 10 - 1, Snake.head.getY() / 10);
				Snake.update();

				break;
			case "RIGHT":
				newHead(Snake.head.getX() / 10 + 1, Snake.head.getY() / 10);
				Snake.update();

				break;

			default:
				break;
			}
		}
	}

	static void newHead(int x, int y) {// makes new head for updating
		Field head = null;
		if (direction.equals("UP") && y < 0) {
			head = Main.grid.get(x).get(Main.gridSize / 10 - 1);
		}
		if (direction.equals("DOWN") && y > Main.gridSize / 10 - 1) {
			head = Main.grid.get(x).get(0);
		}
		if (direction.equals("LEFT") && x < 0) {
			head = Main.grid.get(Main.gridSize / 10 - 1).get(y);
		}
		if (direction.equals("RIGHT") && x > Main.gridSize / 10 - 1) {
			head = Main.grid.get(0).get(y);
		}
		if (head == null) {
			head = Main.grid.get(x).get(y);
		}
		Snake.newHead = head;
	}

}
