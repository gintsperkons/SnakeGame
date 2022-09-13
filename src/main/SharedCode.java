package main;

import java.util.Random;

public class SharedCode {// code used in multiple files/ locations
	public static int getRandom(int min, int max) {
		Random rand = new Random();
		int x = rand.nextInt((max - min)) + min;
		return x;
	}
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
