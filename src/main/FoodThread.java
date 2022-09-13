package main;

public class FoodThread extends Thread {

	public static FoodThread thread = new FoodThread();
    private final Object pauseLock = new Object();
	public static void make() {
		thread = new FoodThread();
		thread.start();
	}

	public void run() {// food generator
		while (Snake.snakeLength < Snake.snakeMaxLength) {
			while (Main.gameOn) {
				synchronized (pauseLock) {
				if(Main.isPaused) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (Food.all.size() >= 3) {
					break;
				}
				int x = SharedCode.getRandom(0, 80);
				int y = SharedCode.getRandom(0, 80);
				if (Main.grid.get(x).get(y).type.equals("NONE")) {
					Main.grid.get(x).get(y).setBackground(Food.color);
					Main.grid.get(x).get(y).type = "FOOD";
					Food.all.add(Main.grid.get(x).get(y));
					break;
				}
				}
			}
			int time = SharedCode.getRandom(2000, 5000);
			SharedCode.pause(time);
		}
	}
	public void resumeThis() {
        synchronized (pauseLock) {
            pauseLock.notifyAll(); // Unblocks thread
        }
    }

}
