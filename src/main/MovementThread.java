package main;

public class MovementThread extends Thread {
	public static MovementThread thread = new MovementThread();
	private final Object pauseLock = new Object();

	public static void make() {
		thread = new MovementThread();
		thread.start();
	}

	public void run() {// movment controls
		while (Snake.snakeLength < Snake.snakeMaxLength && Main.gameOn) {
			synchronized (pauseLock) {
				if (Main.isPaused) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				SharedCode.pause(Snake.speed);
				Movement.move();
			}
		}
	}
	public void resumeThis() {
        synchronized (pauseLock) {
            pauseLock.notifyAll(); // Unblocks thread
        }
    }
}
