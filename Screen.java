import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author Joshua Lawson
 *
 */
public class Screen extends JPanel implements KeyListener {

	public static int screenWidth = 600;
	public static int screenHeight = 500;
	public static ImageIcon backgroundImg = new ImageIcon("paclvl.jpg");
	public static ImageIcon inkyImg = new ImageIcon("inky.gif");
	public static ImageIcon blinkyImg = new ImageIcon("blinky.gif");
	public static ImageIcon pinkyImg = new ImageIcon("pinky.gif");
	public static ImageIcon clydeImg = new ImageIcon("clyde.gif");
	public static ImageIcon blueImg = new ImageIcon("blueghost.gif");
	public static ImageIcon PacmanImg = new ImageIcon("pacman-2.gif");
	public static ImageIcon eyesImg = new ImageIcon("eyes.gif");
	public static ImageIcon deathIMG = new ImageIcon("explosion.gif");
	public static ImageIcon PPImg = new ImageIcon("power.gif");
	
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	
	private Pacman pac;
	private Blinky blinky;
	private Pinky pinky;
	private Inky inky;
	private Clyde clyde;
	private GhostChange changeClyde;
	private GhostChange changeBlinky;
	private GhostChange changePinky;
	private GhostChange changeInky;
	private Eyes eyesBlinky;
	private Eyes eyesPinky;
	private Eyes eyesInky;
	private Eyes eyesClyde;
	
	/**
	 * The screen has a black background.  
	 */
	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		Random generator = new Random();
		
		// generate ghost starting locations
		int xblinky = screenWidth/2 - 40;
		int yblinky = screenHeight/2 - 40;
		int xpinky = screenWidth/2 - 20;
		int ypinky = screenHeight/2 - 40;
		int xinky = screenWidth/2;
		int yinky = screenHeight/2 - 40;
		int xclyde = screenWidth/2 + 20;
		int yclyde = screenHeight/2 - 40;
		// generate size
		int width = 20;
		int height = 20; // trial sizes 
			
		int points = 10; // trial points
			
		pinky = new Pinky(new Point(xpinky, ypinky),
				new Rectangle(width, height),
				points,
				pinkyImg.getImage());
			
		// generate trial vector
		double dx = 0;
		double dy = 0;
		pinky.setVector(new MyVector(dx, dy));
			
		screenObjects.add(pinky);
			
		blinky = new Blinky(new Point(xblinky, yblinky),
				new Rectangle(width, height),
				points,
				blinkyImg.getImage());
			
		// generate trial vector
		double dx1 = 0;
		double dy1 = 0;
		blinky.setVector(new MyVector(dx1, dy1));
			
		screenObjects.add(blinky);
			
		inky = new Inky(new Point(xinky, yinky),
				new Rectangle(width, height),
				points,
				inkyImg.getImage());
			
		// generate trial vector
		double dx2 = 0;
		double dy2 = 0;
		inky.setVector(new MyVector(dx2, dy2));
			
		screenObjects.add(inky);
			
		clyde = new Clyde(new Point(xclyde, yclyde),
				new Rectangle(width, height),
				points,
				clydeImg.getImage());
			
		// generate trial vector
		double dx3 = 0;
		double dy3 = 0;
		clyde.setVector(new MyVector(dx3, dy3));
			
		screenObjects.add(clyde);

		
		// add player's Pacman
		int xpac = screenWidth/2 - 10;
		int ypac = screenHeight/2 + 5;
		pac = new Pacman(new Point(xpac, ypac),
				new Rectangle(20,20), 0,
				PacmanImg.getImage());
		screenObjects.add(pac);
		
		// add Power Pellet
		int xPP = screenWidth/2 - 75;
		int yPP = screenHeight/2 + 75;
		PowerPellet PP = new PowerPellet(new Point(xPP, yPP),
				new Rectangle(15,15), 100,
				PPImg.getImage());
		screenObjects.add(PP);
		

		this.addKeyListener(this);
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		
		super.paintComponent(g);
		g.drawImage(backgroundImg.getImage(), 
				0, 0, screenWidth, screenHeight, null);
		
		// draw objects
		for (ScreenObject obj : screenObjects) {
			obj.draw(g);
		}
	}

	/**
	 * @return the timer
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// move each object
			for (int i = 0; i < screenObjects.size(); i++) {
				ScreenObject obj = screenObjects.get(i);
				if (obj instanceof MovingScreenObject) {
					MovingScreenObject movingObj =
							(MovingScreenObject) obj;
					if( movingObj.getAge() > movingObj.getMaximumage()){
						if(movingObj == changeClyde){
							clyde = new Clyde(movingObj.getLocation(), movingObj.getSize(), 0, clydeImg.getImage());
							screenObjects.add(clyde);
						}
						else if(movingObj == changePinky){
							pinky = new Pinky(movingObj.getLocation(), movingObj.getSize(), 0, pinkyImg.getImage());
							screenObjects.add(pinky);
						}
						else if(movingObj == changeBlinky){
							blinky = new Blinky(movingObj.getLocation(), movingObj.getSize(), 0, blinkyImg.getImage());
							screenObjects.add(blinky);
						}
						else if(movingObj == changeInky){
							inky = new Inky(movingObj.getLocation(), movingObj.getSize(), 0, inkyImg.getImage());
							screenObjects.add(inky);
						}
						screenObjects.remove(movingObj);
					}
					else{
						movingObj.move();
						for(int j = 0; j < screenObjects.size(); j++){
							ScreenObject obj2 = screenObjects.get(j);
							if(obj2 instanceof MovingScreenObject){
								MovingScreenObject moving2 = (MovingScreenObject) obj2;
								if(movingObj == obj2){
									;
								}
								else if(movingObj.collide(moving2)){
									if(movingObj instanceof Enemy && moving2 instanceof Enemy){
										;
									}
									else if(movingObj instanceof Pacman && moving2 instanceof Enemy){
										Dead dead = new Dead(movingObj.getLocation(), movingObj.getSize(), 0, deathIMG.getImage());
										screenObjects.add(dead);
										screenObjects.remove(movingObj);
									}
									else if(movingObj instanceof Pacman && moving2 instanceof PowerPellet){
										changeClyde = new GhostChange(clyde.getLocation(), clyde.getSize(), 200, blueImg.getImage());
										changePinky = new GhostChange(pinky.getLocation(), pinky.getSize(), 200, blueImg.getImage());
										changeBlinky = new GhostChange(blinky.getLocation(), blinky.getSize(), 200, blueImg.getImage());
										changeInky = new GhostChange(inky.getLocation(), inky.getSize(), 200, blueImg.getImage());
										screenObjects.add(changeClyde);
										screenObjects.add(changePinky);
										screenObjects.add(changeBlinky);
										screenObjects.add(changeInky);
										screenObjects.remove(clyde);
										screenObjects.remove(pinky);
										screenObjects.remove(blinky);
										screenObjects.remove(inky);
										screenObjects.remove(moving2);
									}
									else if(movingObj instanceof Pacman && moving2 instanceof GhostChange){
										if(moving2 == changeClyde){
										eyesClyde = new Eyes(changeClyde.getLocation(), changeClyde.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesClyde);
										screenObjects.remove(changeClyde);
										}
										else if(moving2 == changePinky){
										eyesPinky = new Eyes(changePinky.getLocation(), changePinky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesPinky);
										screenObjects.remove(changePinky);
										}
										else if(moving2 == changeBlinky){
										eyesBlinky = new Eyes(changeBlinky.getLocation(), changeBlinky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesBlinky);
										screenObjects.remove(changeBlinky);
										}
										else if(moving2 == changeInky){
										eyesInky = new Eyes(changeInky.getLocation(), changeInky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesInky);
										screenObjects.remove(changeInky);
										}
									}
								}
							}
						}
					}
				}
				double dx = pac.getVector().getChangeX();
				double dy = pac.getVector().getChangeY();
				dx *= 0.996;
				dy *= 0.996;
				if (Math.abs(dx) < 4.75 && Math.abs(dy) < 4.75) {
					dx = 0;
					dy = 0;
				}
				pac.getVector().setChangeX(dx);
				pac.getVector().setChangeY(dy);
			}
			
			repaint();
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		   int keyCode = event.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            // handle up 
		        	pac.setAngle(270);
		        	pac.setVector(new MyVector(0,-5));

		        	break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	pac.setAngle(90);
		        	pac.setVector(new MyVector(0,5));
					
		            break;
		        case KeyEvent.VK_LEFT:
		        	pac.setAngle(180);
		        	pac.setVector(new MyVector(-5,0));
		            
		            break;
		        case KeyEvent.VK_RIGHT :
		        	pac.setAngle(0);
		        	pac.setVector(new MyVector(5,0));
		            
		            break;
		            
		     }
		    repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
