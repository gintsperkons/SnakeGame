package main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Thread {

	static Clip bite;
	static Clip win;
	static Clip lose;
	static Clip music;
	static Clip temp;

	
	
	public static void load () {
		try {
			bite = Sound.loadIn("\\assets\\sounds\\bite.wav");
			win = Sound.loadIn("\\assets\\sounds\\win.wav");

			lose = Sound.loadIn("\\assets\\sounds\\lose.wav");
			music = Sound.loadIn("\\assets\\sounds\\Divine Life Society - Jesse Gallagher.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Clip loadIn(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		try {
		File f = new File(Main.path + soundFile);
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		return clip;
		}catch (Exception e) {
			System.out.println();
		}
		return null;
	}

	public static void play(String text) {
		if (text.equals("BITE")&& Main.sfxOn) {
			temp = bite;
			Sound t = new Sound();
			t.run("SOUND");

		}
		if (text.equals("WIN")) {
			temp = win;
			Sound t = new Sound();
			t.run("SOUND");

		}
		if (text.equals("Lose")) {
			temp = lose;
			Sound t = new Sound();
			t.run("SOUND");

		}
		if (text.equals("MUSIC")) {
			Sound t = new Sound();
			t.run("MUSIC");

		}
	}

	public void run(String type) {
		if (type.equals("SOUND")) {
			playSound();
		}
		if (type.equals("MUSIC")) {
			playMusic();
		}
	}

	private static void playSound() {
		if (temp == null){
			return;
		}
		temp.setMicrosecondPosition(0);
		temp.start();
	}
	public static void playMusic() {
		if (music == null){
			return;
		}
		music.setMicrosecondPosition(0);
		music.start();
	}
	public static void stopMusic() {
		music.stop();
		
	}
}
