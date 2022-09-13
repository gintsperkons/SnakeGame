package main;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
public class Menu {
	static JLabel endScreen = new JLabel();
	static JLabel scoreLabel = new JLabel();
	static JButton buttonPlay = new JButton("Play");
	static JButton buttonStop = new JButton("Stop");
	static JButton buttonPause = new JButton("Stop");
	static JCheckBox SFX = new JCheckBox("SFX");
	static JCheckBox Music = new JCheckBox("Music");
	static JSlider speed = new JSlider(JSlider.HORIZONTAL,
            0, 50, 40);
	public static void create(){// creates menu bar
		
		scoreLabel = makeLabel(Snake.snakeLength+"",10,810,50,50);
		endScreen = makeLabel("",0,0,800,800);
		buttonPlay = makeButton("Play",60,810,100,50);
		buttonStop = makeButton("Stop",170,810,100,50);
		buttonPause = makeButton("Pause",280,810,100,50);
		SFX = makeCheckBox("SFX",400,810,100,20);
		Music = makeCheckBox("Music",400,830,100,20);
		SFX.addItemListener(Main.ml1);
		Music.addItemListener(Main.ml1);
		speed.addChangeListener(Main.ml1);
		speed.setMajorTickSpacing(10);
		speed.setMinorTickSpacing(1);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		speed.setBounds(510,810,280,50);
		speed.setFocusable(false);
		Main.cont.add(speed);
		endScreen.setText("<html><div style='text-align: center;'>"
				+"Controls"
				+ "<br/>You are allowed to move through walls."
				+ "<br/>You are not allowed to run into yourself."
				+ "<br/>You need to collect food and grow to maximum size."
				+ "<br/>Loding..."
				+  "</div></html>");
		endScreen.setIcon(new ImageIcon(Main.path+"\\assets\\images\\controls.png"));
		endScreen.setFont(new Font("Serif", Font.BOLD, 32));
		Main.frame.repaint();
		
		
	}
	public static JCheckBox makeCheckBox(String text,int posX, int posY, int width, int height) {
		JCheckBox f = new JCheckBox(text);
		f.setBounds(posX,posY,width,height);
        f.setSelected(true);
        f.setFocusable(false);
        f.setVisible(true);
        Main.cont.add(f);
        
        return f;
	}
	public static void scoreAdd(int i) {
		Snake.snakeLength+= i;
		scoreLabel.setText(Snake.snakeLength+"");
	}
	public static JLabel makeLabel(String text,int posX, int posY, int width, int height) {
		JLabel f = new JLabel();
		f.setBounds(posX,posY,width,height);
		f.setText(text);
		f.setFont(new Font("Serif", Font.BOLD, 20));
		f.setHorizontalAlignment(SwingConstants.CENTER);
		f.setVerticalTextPosition(JLabel.BOTTOM);
		f.setHorizontalTextPosition(JLabel.CENTER);
		Main.cont.add(f);
		return f;
	}
	public static JButton makeButton(String text,int posX, int posY, int width, int height) {
		JButton f = new JButton(text);
		f.setBounds(posX,posY,width,height);
		f.setText(text);
		f.setFocusable(false);
		f.addActionListener(Main.ml1);
		Main.cont.add(f);
		f.setEnabled(false);
		return f;
	}
}
