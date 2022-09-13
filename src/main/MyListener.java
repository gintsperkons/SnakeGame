package main;

import java.awt.event.*;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyListener extends Thread implements ActionListener,ItemListener, KeyListener, ChangeListener {

	int x;

	public void keyTyped(KeyEvent event) {

	}

	public void keyPressed(KeyEvent event) {
		x = event.getKeyCode();
		if ((x == KeyEvent.VK_D || x == KeyEvent.VK_RIGHT) && !Movement.direction.equals("LEFT")) {
			Movement.direction = "RIGHT";
		}

		if ((x == KeyEvent.VK_A || x == KeyEvent.VK_LEFT) && !Movement.direction.equals("RIGHT")) {
			Movement.direction = "LEFT";
		}
		if ((x == KeyEvent.VK_S || x == KeyEvent.VK_DOWN) && !Movement.direction.equals("UP")) {
			Movement.direction = "DOWN";
		}

		if ((x == KeyEvent.VK_UP || x == KeyEvent.VK_W) && !Movement.direction.equals("DOWN")) {
			Movement.direction = "UP";
		}
	}

	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Menu.buttonPlay) {
			Main.gameOn = true;
			Menu.buttonPlay.setEnabled(false);
			Menu.buttonStop.setEnabled(true);
			Menu.buttonPause.setEnabled(true);
		}
		if (e.getSource() == Menu.buttonStop) {
			Main.stop();
		}
		if (e.getSource() == Menu.buttonPause) {
			if (Main.isPaused) {
				FoodThread.thread.resumeThis();
				MovementThread.thread.resumeThis();
			}
			Main.isPaused = !Main.isPaused;
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == Menu.speed) {
			if (Main.gameOn) {
				return;
			}
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting()) {
				int x = (int) source.getValue();
				x *= 10;
				x = 500 - x;
				if (x <= 0) {
					x = 1;
				}
				Snake.speed = x;
			}
		}

	}

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (source == Menu.SFX) {
			Main.sfxOn = !Main.sfxOn;
		}
		if (source == Menu.Music) {
			Main.musicOn = !Main.musicOn;
			if (Main.musicOn) {
				Sound.play("MUSIC");
			}
			if (!Main.musicOn) {
				Sound.stopMusic();
			}
			
		}
	}
}
