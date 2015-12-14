import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * 
 */

/**
 * Creates the title screen
 * @author Jsg
 *
 */
public class TitleScreen extends JFrame {
	private JLabel instructions;
	private JPanel panel;
	Image img;
	ImageIcon icon;
	JLabel wall;
	AudioClip theme;
	/**
	 * checks to see if the exception is thrown
	 * @throws FileNotFoundException
	 */
	public TitleScreen() throws FileNotFoundException {
		setTitle("PACMAN");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);	
	}
	
	/**
	 * Builds the Panel that Pacman is displayed upon.
	 */
	private void buildPanel() {
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		icon = new ImageIcon("pacmanimg.png");
		wall = new JLabel();
		wall.setIcon(icon);
		panel.add(wall);
		add(panel);
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GameWindow window = new GameWindow();
				window.setResizable(false);
				TitleScreen.this.setVisible(false);
				
			}
		});
		panel.add(play);
		
		JButton close = new JButton("Exit");
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
				}
		});		
		panel.add(close);
		try {
			theme=Applet.newAudioClip(new URL("file:pacman_beginning.wav"));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		theme.play();
	}
	
	/**
	 * runs the program and actually displays the start screen.
	 * @param args ignored
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		TitleScreen window = new TitleScreen();
		

	}

}
