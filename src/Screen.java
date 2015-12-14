import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

	public static int screenWidth = 560;
	public static int screenHeight = 620;
	public static ImageIcon inkyImg = new ImageIcon("inky.gif");
	public static ImageIcon blinkyImg = new ImageIcon("blinky.gif");
	public static ImageIcon pinkyImg = new ImageIcon("pinky.gif");
	public static ImageIcon clydeImg = new ImageIcon("clyde.gif");
	public static ImageIcon blueImg = new ImageIcon("blueghost.gif");
	public static ImageIcon PacmanImg = new ImageIcon("pacman-2.gif");
	public static ImageIcon eyesImg = new ImageIcon("eyes.gif");
	public static ImageIcon deathIMG = new ImageIcon("explosion.gif");
	public static ImageIcon PPImg = new ImageIcon("power.gif");
	public static ImageIcon wallImg = new ImageIcon("blueWall.png");
	public static ImageIcon blackBox = new ImageIcon("blackbox.png");
	
	public static ImageIcon appleImg = new ImageIcon("apple.png");
	public static ImageIcon bananaImg = new ImageIcon("banana.png");
	public static ImageIcon cherryImg = new ImageIcon("cherry.png");
	public static ImageIcon orangeImg = new ImageIcon("orange.png");
	public static ImageIcon pearImg = new ImageIcon("pear.png");
	public static ImageIcon pretzelImg = new ImageIcon("pretzel.png");
	public static ImageIcon strawberryImg = new ImageIcon("strawberry.png");
	
	private ArrayList<ScreenObject> screenObjects;
	private ArrayList<Wall> walls;
	private ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	private javax.swing.Timer timer;
	private javax.swing.Timer ghostTimer;
	
	private Enemy enemy;
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
	private Wall wall;
	private int Lives = 3;
	private static ImageIcon failure = new ImageIcon("GameOver.gif");
	private static ImageIcon victory = new ImageIcon("winner.png");

	private int score;
	private Fruit apple;
	private Fruit banana;
	private Fruit cherry;
	private Fruit orange;
	private Fruit pear;
	private Fruit pretzel;
	private Fruit strawberry;
	Random generator = new Random();
	List<Integer> ghostDirections = new ArrayList<Integer>();
	List<Integer> ghostDirections2 = new ArrayList<Integer>();
	List<Integer> ghostDirections3 = new ArrayList<Integer>();
	List<Integer> ghostDirections4 = new ArrayList<Integer>();
	
	
	/**
	 * The screen has a maze background.  
	 */
	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		walls = new ArrayList<Wall>(48);
		screenObjects = new ArrayList<ScreenObject>();
		//Random generator = new Random();
		
		
		// generate ghost starting locations
		int xblinky = screenWidth/2 - 30;
		int yblinky = screenHeight/2 - 90;
		int xpinky = screenWidth/2 - 20;
		int ypinky = screenHeight/2 - 90;
		int xinky = screenWidth/2;
		int yinky = screenHeight/2 - 90;
		int xclyde = screenWidth/2 + 10;
		int yclyde = screenHeight/2 - 90;
		
		int xeyesblinky = screenWidth/2 - 30;
		int yeyesblinky = screenHeight/2 - 30;
		int xeyespinky = screenWidth/2 - 20;
		int yeyespinky = screenHeight/2 - 30;
		int xeyesinky = screenWidth/2;
		int yeyesinky = screenHeight/2 - 30;
		int xeyesclyde = screenWidth/2 + 10;
		int yeyesclyde = screenHeight/2 - 30;
		
		
		// generate size
		int width = 20;
		int height = 20; // trial sizes 

		int points = 10; // trial points
		
		//screenObjects.add(scoreDisplay);

		//List<Integer> ghostDirections = new ArrayList<Integer>();
		ghostDirections2.add(180);
		ghostDirections2.add(0);
		ghostDirections2.add(270);
		ghostDirections.add(90);
		ghostDirections.add(180);
		ghostDirections.add(0);
		ghostDirections3.add(180);
		ghostDirections3.add(270);
		ghostDirections3.add(90);
		ghostDirections4.add(90);
		ghostDirections4.add(0);
		ghostDirections4.add(270);

		
		pinky = new Pinky(new Point(xpinky, ypinky),
				new Rectangle(width, height),
				points,
				pinkyImg.getImage());
		
		eyesPinky = new Eyes(new Point(xeyespinky, yeyespinky),
				new Rectangle(width, height),
				points,
				eyesImg.getImage());

		// generate trial vector
		pinky.setAngle(180);
		pinky.setVector(new MyVector(-2, 0));
		screenObjects.add(pinky);

		blinky = new Blinky(new Point(xblinky, yblinky),
				new Rectangle(width, height),
				points,
				blinkyImg.getImage());
		
		eyesBlinky = new Eyes(new Point(xeyesblinky, yeyesblinky),
				new Rectangle(width, height),
				points,
				eyesImg.getImage());

		// generate trial vector
		blinky.setAngle(0);
		blinky.setVector(new MyVector(2, 0));
		screenObjects.add(blinky);

		inky = new Inky(new Point(xinky, yinky),
				new Rectangle(width, height),
				points,
				inkyImg.getImage());
		
		eyesInky = new Eyes(new Point(xeyesinky, yeyesinky),
				new Rectangle(width, height),
				points,
				eyesImg.getImage());

		// generate trial vector
		inky.setAngle(180);
		inky.setVector(new MyVector(-2,0));
		screenObjects.add(inky);

		clyde = new Clyde(new Point(xclyde, yclyde),
				new Rectangle(width, height),
				points,
				clydeImg.getImage());
		
		eyesClyde = new Eyes(new Point(xeyesclyde, yeyesclyde),
				new Rectangle(width, height),
				points,
				eyesImg.getImage());

		// generate trial vector
		clyde.setAngle(0);
		clyde.setVector(new MyVector(2,0));
		screenObjects.add(clyde);
		
		
		//generate fruit starting locations
		int xfruit = 270;
		int yfruit = 220;
		
		//add Fruits
		apple = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, appleImg.getImage());
		double dxFruit = 0;
		double dyFruit = 0;
		apple.setVector(new MyVector (dxFruit, dyFruit));
		fruits.add(apple);
		banana = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, bananaImg.getImage());
		fruits.add(banana);
		cherry = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, cherryImg.getImage());
		fruits.add(cherry);
		orange = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, orangeImg.getImage());
		fruits.add(orange);
		pear = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, pearImg.getImage());
		fruits.add(pear);
		pretzel = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, pretzelImg.getImage());
		fruits.add(pretzel);
		strawberry = new Fruit(new Point (xfruit, yfruit), new Rectangle(width,height),points, strawberryImg.getImage());
		fruits.add(strawberry);

		screenObjects.add(fruits.get(generator.nextInt(7)));
		
		
		// add player's Pacman
		int xpac = screenWidth/2 - 10;
		int ypac = screenHeight/2 + 30;
		pac = new Pacman(new Point(xpac, ypac),
				new Rectangle(20,20), 0,
				PacmanImg.getImage());
		screenObjects.add(pac);
		
		
		PowerPellet PP = new PowerPellet(new Point(20, 20),
				new Rectangle(25,25), 100,
				PPImg.getImage());
		screenObjects.add(PP);
		
		PowerPellet PP1 = new PowerPellet(new Point(520, 20),
				new Rectangle(25,25), 100,
				PPImg.getImage());
		screenObjects.add(PP1);
		
		PowerPellet PP2 = new PowerPellet(new Point(20, 580),
				new Rectangle(25,25), 100,
				PPImg.getImage());
		screenObjects.add(PP2);
		
		PowerPellet PP3 = new PowerPellet(new Point(520, 580),
				new Rectangle(25,25), 100,
				PPImg.getImage());
		screenObjects.add(PP3);
		
		//Generates the pacfood objects on the screen
		for(int i =20; i <560; i+=20){
			for(int y=25; y<620; y +=20){
				pacfood food = new pacfood(new Point(i, y), new Rectangle(15,15), 100, PPImg.getImage());
				screenObjects.add(food);
			}
		}
		
		//add Walls
		wall = new Wall(new Point(0,0),new Rectangle(560,20),0,wallImg.getImage());
		screenObjects.add(wall);

		Wall wall2 = new Wall(new Point(0,600),new Rectangle(560,20),0,wallImg.getImage());
		screenObjects.add(wall2);
		
		Wall wall3 = new Wall(new Point(260,0),new Rectangle(40,95),0,wallImg.getImage());
		screenObjects.add(wall3);
		
		Wall wall4 = new Wall(new Point(260,480),new Rectangle(40,100),0,wallImg.getImage());
		screenObjects.add(wall4);
		
		Wall wall5 = new Wall(new Point(380,300),new Rectangle(40,100),0,wallImg.getImage());
		screenObjects.add(wall5);
		
		Wall wall6 = new Wall(new Point(140,300),new Rectangle(40,100),0,wallImg.getImage());
		screenObjects.add(wall6);
		
		Wall wall7 = new Wall(new Point(260,125),new Rectangle(40,95),0,wallImg.getImage());
		screenObjects.add(wall7);
		
		Wall wall8 = new Wall(new Point(140,120),new Rectangle(40,150),0,wallImg.getImage());
		screenObjects.add(wall8);
		
		Wall wall9 = new Wall(new Point(380,120),new Rectangle(40,150),0,wallImg.getImage());
		screenObjects.add(wall9);
		
		Wall wall10 = new Wall(new Point(0,0),new Rectangle(20,195),0,wallImg.getImage());
		screenObjects.add(wall10);
		
		Wall wall11 = new Wall(new Point(0,380),new Rectangle(20,240),0,wallImg.getImage());
		screenObjects.add(wall11);
		
		Wall wall12 = new Wall(new Point(540,380),new Rectangle(20,240),0,wallImg.getImage());
		screenObjects.add(wall12);
		
		Wall wall13 = new Wall(new Point(540,0),new Rectangle(20,195),0,wallImg.getImage());
		screenObjects.add(wall13);
		
		Wall wall14 = new Wall(new Point(100,185),new Rectangle(20,210),0,wallImg.getImage());
		screenObjects.add(wall14);
		
		Wall wall15 = new Wall(new Point(440,185),new Rectangle(20,210),0,wallImg.getImage());
		screenObjects.add(wall15);
		
		Wall wall16 = new Wall(new Point(440,185),new Rectangle(120,20),0,wallImg.getImage());
		screenObjects.add(wall16);
		
		Wall wall17 = new Wall(new Point(0,185),new Rectangle(100,20),0,wallImg.getImage());
		screenObjects.add(wall17);
		
		Wall wall18 = new Wall(new Point(0,185),new Rectangle(100,20),0,wallImg.getImage());
		screenObjects.add(wall18);
		
		Wall wall19 = new Wall(new Point(0,380),new Rectangle(120,20),0,wallImg.getImage());
		screenObjects.add(wall19);
		
		Wall wall20 = new Wall(new Point(440,380),new Rectangle(120,20),0,wallImg.getImage());
		screenObjects.add(wall20);
		
		Wall wall21 = new Wall(new Point(40,540),new Rectangle(195,40),0,wallImg.getImage());
		screenObjects.add(wall21);
		
		Wall wall22 = new Wall(new Point(320,540),new Rectangle(195,40),0,wallImg.getImage());
		screenObjects.add(wall22);
		
		Wall wall23 = new Wall(new Point(320,420),new Rectangle(100,40),0,wallImg.getImage());
		screenObjects.add(wall23);
		
		Wall wallq = new Wall(new Point(440,420),new Rectangle(80,40),0,wallImg.getImage());
		screenObjects.add(wallq);
		
		Wall wall24 = new Wall(new Point(40,420),new Rectangle(75,40),0,wallImg.getImage());
		screenObjects.add(wall24);
		
		Wall wallz = new Wall(new Point(140,420),new Rectangle(95,40),0,wallImg.getImage());
		screenObjects.add(wallz);
		
		Wall wall25 = new Wall(new Point(200,480),new Rectangle(160,35),0,wallImg.getImage());
		screenObjects.add(wall25);
		
		Wall wall26 = new Wall(new Point(200,120),new Rectangle(160,35),0,wallImg.getImage());
		screenObjects.add(wall26);
		
		Wall wall27 = new Wall(new Point(205,360),new Rectangle(150,35),0,wallImg.getImage());
		screenObjects.add(wall27);
		
		Wall wall28 = new Wall(new Point(160,180),new Rectangle(75,35),0,wallImg.getImage());
		screenObjects.add(wall28);
		
		Wall wall29 = new Wall(new Point(325,180),new Rectangle(75,35),0,wallImg.getImage());
		screenObjects.add(wall29);
		
		Wall wall30 = new Wall(new Point(45,45),new Rectangle(65,50),0,wallImg.getImage());
		screenObjects.add(wall30);
		
		Wall wall31 = new Wall(new Point(445,45),new Rectangle(70,50),0,wallImg.getImage());
		screenObjects.add(wall31);
		
		Wall wall32 = new Wall(new Point(325,45),new Rectangle(95,50),0,wallImg.getImage());
		screenObjects.add(wall32);
		
		Wall wall33 = new Wall(new Point(140,45),new Rectangle(95,50),0,wallImg.getImage());
		screenObjects.add(wall33);
		
		Wall wall34 = new Wall(new Point(45,120),new Rectangle(65,35),0,wallImg.getImage());
		screenObjects.add(wall34);
		
		Wall wall35 = new Wall(new Point(445,120),new Rectangle(70,35),0,wallImg.getImage());
		screenObjects.add(wall35);
		
		Wall wall36 = new Wall(new Point(85,420),new Rectangle(30, 95),0,wallImg.getImage());
		screenObjects.add(wall36);
		
		Wall wall37 = new Wall(new Point(140,480),new Rectangle(35, 95),0,wallImg.getImage());
		screenObjects.add(wall37);
		
		Wall wall38 = new Wall(new Point(260,380),new Rectangle(35, 75),0,wallImg.getImage());
		screenObjects.add(wall38);
		
		Wall wall39 = new Wall(new Point(440,420),new Rectangle(35, 95),0,wallImg.getImage());
		screenObjects.add(wall39);
		
		Wall wall40 = new Wall(new Point(380,480),new Rectangle(35, 95),0,wallImg.getImage());
		screenObjects.add(wall40);
		
		Wall wall41 = new Wall(new Point(0,480),new Rectangle(60, 35),0,wallImg.getImage());
		screenObjects.add(wall41);
		
		Wall wall42 = new Wall(new Point(500,480),new Rectangle(60, 35),0,wallImg.getImage());
		screenObjects.add(wall42);
		
		Wall wall44 = new Wall(new Point(200,240),new Rectangle(160, 20),0,wallImg.getImage());
		screenObjects.add(wall44);
		
		Wall wall45 = new Wall(new Point(200,320),new Rectangle(160, 20),0,wallImg.getImage());
		screenObjects.add(wall45);
		
		Wall wall46 = new Wall(new Point(200,240),new Rectangle(20, 100),0,wallImg.getImage());
		screenObjects.add(wall46);
		
		Wall wall47 = new Wall(new Point(340,240),new Rectangle(20, 100),0,wallImg.getImage());
		screenObjects.add(wall47);
		
		//Left most block of black space.
		//use for points
		//drawstring for points in this location	
		Wall wall48 = new Wall(new Point(0,200),new Rectangle(110,180),0,null);
		//g.drawString(String.valueOf(score), 25, 25);
		screenObjects.add(wall48);
		
		//Right most block of black space.
		//use for lives
		Wall wall49 = new Wall(new Point(450,200),new Rectangle(110,180),0,null);
		screenObjects.add(wall49);
		
		//This is the corral block to remove the pellets inside. 
		//Needs to be changed to a different type of object so the ghosts can spawn on it.
		Corral wall50 = new Corral(new Point(220,260),new Rectangle(120,60),0,null);
		screenObjects.add(wall50);
		
		this.addKeyListener(this);
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
		ghostTimer = new javax.swing.Timer(15000, new TimerListener());
		ghostTimer.start();
		
	}
	
	/**
	 * this method draws the objects on the screen.
	 * @param g this is the graphical component of the method paint component
	 */
	public void paintComponent(Graphics g) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		
		super.paintComponent(g);
		g.drawString(String.valueOf(score), 25, 25);
		
		// draw objects
		for (ScreenObject obj : screenObjects) {
			obj.draw(g);
		}
		g.setColor(Color.white);
		g.drawString(String.valueOf("Score: " + score), 0, 300);
		g.drawString(String.valueOf("Lives: " + Lives), 475, 300);
		g.drawImage(PacmanImg.getImage(), 480, 300, null);		
	}

	/**
	 * returns the time is on the timer.
	 * @return the timer 
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * assigns a new time to the timer.
	 * @param timer the timer to set
	 */
	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * 
	 * creates a new action listener for the timer Listener
	 *
	 */
	private class TimerListener implements ActionListener {

		@Override
		/**
		 *huge block of undocumented code. It handles the collision for all the moving screen objects
		 *@param e listens to the event that is happening.
		 */
		public void actionPerformed(ActionEvent e) {
	

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
							clyde.setVector(new MyVector(2,0));
						}
						if(movingObj == eyesClyde){
							clyde = new Clyde(movingObj.getLocation(), movingObj.getSize(), 0, clydeImg.getImage());
							screenObjects.add(clyde);
							clyde.setVector(new MyVector(2,0));
						}
						else if(movingObj == changePinky){
							pinky = new Pinky(movingObj.getLocation(), movingObj.getSize(), 0, pinkyImg.getImage());
							screenObjects.add(pinky);
							pinky.setVector(new MyVector(2,0));
						}
						if(movingObj == eyesPinky){
							pinky = new Pinky(movingObj.getLocation(), movingObj.getSize(), 0, pinkyImg.getImage());
							screenObjects.add(pinky);
							pinky.setVector(new MyVector(2,0));
						}
						else if(movingObj == changeBlinky){
							blinky = new Blinky(movingObj.getLocation(), movingObj.getSize(), 0, blinkyImg.getImage());
							screenObjects.add(blinky);
							blinky.setVector(new MyVector(2,0));
						}
						if(movingObj == eyesBlinky){
							blinky = new Blinky(movingObj.getLocation(), movingObj.getSize(), 0, blinkyImg.getImage());
							screenObjects.add(blinky);
							blinky.setVector(new MyVector(2,0));
						}
						else if(movingObj == changeInky){
							inky = new Inky(movingObj.getLocation(), movingObj.getSize(), 0, inkyImg.getImage());
							screenObjects.add(inky);
							inky.setVector(new MyVector(2,0));
						}
						if(movingObj == eyesInky){
							inky = new Inky(movingObj.getLocation(), movingObj.getSize(), 0, inkyImg.getImage());
							screenObjects.add(inky);
							inky.setVector(new MyVector(2,0));
						}
						screenObjects.remove(movingObj);
					}
					else{
						movingObj.move();
						for(int j = 0; j < screenObjects.size(); j++){
							ScreenObject obj2 = screenObjects.get(j);
							if(obj2 instanceof MovingScreenObject || obj2 instanceof ScreenObject){
								MovingScreenObject moving2 = (MovingScreenObject) obj2;
								if(movingObj == obj2){
									;
								}
								else if(movingObj.collide(moving2)){
									if(movingObj instanceof Enemy && moving2 instanceof Enemy){
										;
									}
									else if(movingObj instanceof Pacman && moving2 instanceof pacfood){
										screenObjects.remove(moving2);
										score += 10;
										//System.out.println(score);
										if(pac.getVector().getChangeX() > 0 || pac.getVector().getChangeY() > 0 || pac.getVector().getChangeX() < 0 || pac.getVector().getChangeY() < 0){
											try {
												AudioClip waka=Applet.newAudioClip(new URL("file:pacman_chomp.wav"));
												waka.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
											
										}
									}
									else if(movingObj instanceof Pacman && moving2 instanceof Fruit){
										screenObjects.remove(moving2);
										if(moving2 == cherry){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=100;
										}
										else if(moving2 == strawberry){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=300;
										}
										else if(moving2 == orange){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=500;
										}
										else if(moving2 == apple){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=700;
										}
										else if(moving2 == banana){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=1000;
										}
										else if(moving2 == pear){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=2000;
										}
										else if(moving2 == pretzel){
											try {
												AudioClip fruitChomp=Applet.newAudioClip(new URL("file:pacman_eatfruit.wav"));
												fruitChomp.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											score+=3000;
										}
										//System.out.println(score);
										if(pac.getVector().getChangeX() > 0 || pac.getVector().getChangeY() > 0 || pac.getVector().getChangeX() < 0 || pac.getVector().getChangeY() < 0){
											try {
												AudioClip waka=Applet.newAudioClip(new URL("file:pacman_chomp.wav"));
												waka.play();
											} catch (MalformedURLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
											
										}
										//ADD POINTS HERE
									}
									
									else if(movingObj instanceof PowerPellet && moving2 instanceof pacfood){
										screenObjects.remove(moving2);
									}
									else if(movingObj instanceof pacfood && moving2 instanceof Corral){
										screenObjects.remove(movingObj);
									}
									else if(movingObj instanceof pacfood && moving2 instanceof Wall){
										screenObjects.remove(movingObj);
									}
									else if(movingObj instanceof Pacman && moving2 instanceof Wall){
										pac.setVector(new MyVector(0,0));
										if(pac.getAngle() == 270){
											pac.setLocation(new Point(pac.getLocation().x,pac.getLocation().y + 2));
										}
										else if(pac.getAngle() == 90){
											pac.setLocation(new Point(pac.getLocation().x,pac.getLocation().y - 2));
										}
										else if(pac.getAngle() == 0){
											pac.setLocation(new Point(pac.getLocation().x - 2,pac.getLocation().y));
										}
										if(pac.getAngle() == 180){
											pac.setLocation(new Point(pac.getLocation().x + 2,pac.getLocation().y ));
										}
									}
									else if(movingObj instanceof Blinky && moving2 instanceof Wall){
										blinky.setVector(new MyVector(0,0));
										if(blinky.getAngle() == 270){
											blinky.setLocation(new Point(blinky.getLocation().x,blinky.getLocation().y + 2));
											blinky.setAngle(ghostDirections.get(generator.nextInt(3)));
											if(blinky.getAngle()== 180){
												blinky.setVector(new MyVector(-2,0));
											}
											else if(blinky.getAngle()== 0){
												blinky.setVector(new MyVector(2,0));
											}
											else if(blinky.getAngle()== 90){
												blinky.setVector(new MyVector(0,2));
											}
										}
										else if(blinky.getAngle() == 90){
											blinky.setLocation(new Point(blinky.getLocation().x,blinky.getLocation().y - 2));
											blinky.setAngle(ghostDirections2.get(generator.nextInt(3)));
											if(blinky.getAngle()== 180){
												blinky.setVector(new MyVector(-2,0));
											}
											else if(blinky.getAngle()== 270){
												blinky.setVector(new MyVector(0,-2));
											}
											else if(blinky.getAngle()== 0){
												blinky.setVector(new MyVector(2,0));
											}
										}
										else if(blinky.getAngle() == 0){
											blinky.setLocation(new Point(blinky.getLocation().x - 2,blinky.getLocation().y));
											blinky.setAngle(ghostDirections3.get(generator.nextInt(3)));
											if(blinky.getAngle()== 270){
												blinky.setVector(new MyVector(0,-2));
											}
											else if(blinky.getAngle()== 90){
												blinky.setVector(new MyVector(0,2));
											}
											else if(blinky.getAngle()== 180){
												blinky.setVector(new MyVector(-2,0));
											}
										}
										else if(blinky.getAngle() == 180){
											blinky.setLocation(new Point(blinky.getLocation().x + 2,blinky.getLocation().y ));
											blinky.setAngle(ghostDirections4.get(generator.nextInt(3)));
											if(blinky.getAngle()== 270){
												blinky.setVector(new MyVector(0,-2));
											}
											else if(blinky.getAngle()== 0){
												blinky.setVector(new MyVector(2,0));
											}
											else if(blinky.getAngle()== 90){
												blinky.setVector(new MyVector(0,2));
											}
										}
									}
									else if(movingObj instanceof Pinky && moving2 instanceof Wall){
										pinky.setVector(new MyVector(0,0));
										if(pinky.getAngle() == 270){
											pinky.setLocation(new Point(pinky.getLocation().x,pinky.getLocation().y + 2));
											pinky.setAngle(ghostDirections.get(generator.nextInt(3)));
											if(pinky.getAngle()== 180){
												pinky.setVector(new MyVector(-2,0));
											}
											else if(pinky.getAngle()== 0){
												pinky.setVector(new MyVector(2,0));
											}
											else if(pinky.getAngle()== 90){
												pinky.setVector(new MyVector(0,2));
											}
										}
										else if(pinky.getAngle() == 90){
											pinky.setLocation(new Point(pinky.getLocation().x,pinky.getLocation().y - 2));
											pinky.setAngle(ghostDirections2.get(generator.nextInt(3)));
											if(pinky.getAngle()== 180){
												pinky.setVector(new MyVector(-2,0));
											}
											else if(pinky.getAngle()== 270){
												pinky.setVector(new MyVector(0,-2));
											}
											else if(pinky.getAngle()== 0){
												pinky.setVector(new MyVector(2,0));
											}
										}
										else if(pinky.getAngle() == 0){
											pinky.setLocation(new Point(pinky.getLocation().x - 2,pinky.getLocation().y));
											pinky.setAngle(ghostDirections3.get(generator.nextInt(3)));
											if(pinky.getAngle()== 270){
												pinky.setVector(new MyVector(0,-2));
											}
											else if(pinky.getAngle()== 90){
												pinky.setVector(new MyVector(0,2));
											}
											else if(pinky.getAngle()== 180){
												pinky.setVector(new MyVector(-2,0));
											}
										}
										else if(pinky.getAngle() == 180){
											pinky.setLocation(new Point(pinky.getLocation().x + 2,pinky.getLocation().y ));
											pinky.setAngle(ghostDirections4.get(generator.nextInt(3)));
											if(pinky.getAngle()== 270){
												pinky.setVector(new MyVector(0,-2));
											}
											else if(pinky.getAngle()== 0){
												pinky.setVector(new MyVector(2,0));
											}
											else if(pinky.getAngle()== 90){
												pinky.setVector(new MyVector(0,2));
											}
										}
									}
									
									else if(movingObj instanceof Inky && moving2 instanceof Wall){
										inky.setVector(new MyVector(0,0));
										if(inky.getAngle() == 270){
											inky.setLocation(new Point(inky.getLocation().x,inky.getLocation().y + 2));
											inky.setAngle(ghostDirections.get(generator.nextInt(3)));
											if(inky.getAngle()== 180){
												inky.setVector(new MyVector(-2,0));
											}
											else if(inky.getAngle()== 0){
												inky.setVector(new MyVector(2,0));
											}
											else if(inky.getAngle()== 90){
												inky.setVector(new MyVector(0,2));
											}
										}
										else if(inky.getAngle() == 90){
											inky.setLocation(new Point(inky.getLocation().x,inky.getLocation().y - 2));
											inky.setAngle(ghostDirections2.get(generator.nextInt(3)));
											if(inky.getAngle()== 180){
												inky.setVector(new MyVector(-2,0));
											}
											else if(inky.getAngle()== 270){
												inky.setVector(new MyVector(0,-2));
											}
											else if(inky.getAngle()== 0){
												inky.setVector(new MyVector(2,0));
											}
										}
										else if(inky.getAngle() == 0){
											inky.setLocation(new Point(inky.getLocation().x - 2,inky.getLocation().y));
											inky.setAngle(ghostDirections3.get(generator.nextInt(3)));
											if(inky.getAngle()== 270){
												inky.setVector(new MyVector(0,-2));
											}
											else if(inky.getAngle()== 90){
												inky.setVector(new MyVector(0,2));
											}
											else if(inky.getAngle()== 180){
												inky.setVector(new MyVector(-2,0));
											}
										}
										else if(inky.getAngle() == 180){
											inky.setLocation(new Point(inky.getLocation().x + 2,inky.getLocation().y ));
											inky.setAngle(ghostDirections4.get(generator.nextInt(3)));
											if(inky.getAngle()== 270){
												inky.setVector(new MyVector(0,-2));
											}
											else if(inky.getAngle()== 0){
												inky.setVector(new MyVector(2,0));
											}
											else if(inky.getAngle()== 90){
												inky.setVector(new MyVector(0,2));
											}
										}
									}
									
									else if(movingObj instanceof Clyde && moving2 instanceof Wall){
										clyde.setVector(new MyVector(0,0));
										if(clyde.getAngle() == 270){
											clyde.setLocation(new Point(clyde.getLocation().x,clyde.getLocation().y + 2));
											clyde.setAngle(ghostDirections.get(generator.nextInt(3)));
											if(clyde.getAngle()== 180){
												clyde.setVector(new MyVector(-2,0));
											}
											else if(clyde.getAngle()== 0){
												clyde.setVector(new MyVector(2,0));
											}
											else if(clyde.getAngle()== 90){
												clyde.setVector(new MyVector(0,2));
											}
										}
										else if(clyde.getAngle() == 90){
											clyde.setLocation(new Point(clyde.getLocation().x,clyde.getLocation().y - 2));
											clyde.setAngle(ghostDirections2.get(generator.nextInt(3)));
											if(clyde.getAngle()== 180){
												clyde.setVector(new MyVector(-2,0));
											}
											else if(clyde.getAngle()== 270){
												clyde.setVector(new MyVector(0,-2));
											}
											else if(clyde.getAngle()== 0){
												clyde.setVector(new MyVector(2,0));
											}
										}
										else if(clyde.getAngle() == 0){
											clyde.setLocation(new Point(clyde.getLocation().x - 2,clyde.getLocation().y));
											clyde.setAngle(ghostDirections3.get(generator.nextInt(3)));
											if(clyde.getAngle()== 270){
												clyde.setVector(new MyVector(0,-2));
											}
											else if(clyde.getAngle()== 90){
												clyde.setVector(new MyVector(0,2));
											}
											else if(clyde.getAngle()== 180){
												clyde.setVector(new MyVector(-2,0));
											}
										}
										else if(clyde.getAngle() == 180){
											clyde.setLocation(new Point(clyde.getLocation().x + 2,clyde.getLocation().y ));
											clyde.setAngle(ghostDirections4.get(generator.nextInt(3)));
											if(clyde.getAngle()== 270){
												clyde.setVector(new MyVector(0,-2));
											}
											else if(clyde.getAngle()== 0){
												clyde.setVector(new MyVector(2,0));
											}
											else if(clyde.getAngle()== 90){
												clyde.setVector(new MyVector(0,2));
											}
										}
									}
									else if(movingObj instanceof Pacman && moving2 instanceof Enemy){
										Dead dead = new Dead(movingObj.getLocation(), movingObj.getSize(), 0, deathIMG.getImage());
										screenObjects.add(dead);
										screenObjects.remove(movingObj);
										Lives --;
										int xpac = screenWidth/2 - 10;
										int ypac = screenHeight/2 + 30;
										pac = new Pacman(new Point(xpac, ypac),
												new Rectangle(20,20), 0,
												PacmanImg.getImage());
										screenObjects.add(pac);
										try {
											AudioClip death=Applet.newAudioClip(new URL("file:pacman_death-2.wav"));
											death.play();
										} catch (MalformedURLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									else if(movingObj instanceof Pacman && moving2 instanceof PowerPellet){
										changeClyde = new GhostChange(clyde.getLocation(), clyde.getSize(), 200, blueImg.getImage());
										changePinky = new GhostChange(pinky.getLocation(), pinky.getSize(), 200, blueImg.getImage());
										changeBlinky = new GhostChange(blinky.getLocation(), blinky.getSize(), 200, blueImg.getImage());
										changeInky = new GhostChange(inky.getLocation(), inky.getSize(), 200, blueImg.getImage());
										changeClyde.setVector(new MyVector(2,0));
										changePinky.setVector(new MyVector(2,0));
										changeInky.setVector(new MyVector(2,0));
										changeBlinky.setVector(new MyVector(2,0));
										score += 50;
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
									else if(movingObj instanceof GhostChange && moving2 instanceof Wall){
										if( movingObj == changeBlinky){
											changeBlinky.setVector(new MyVector(0,0));
											if(changeBlinky.getAngle() == 270){
												changeBlinky.setLocation(new Point(changeBlinky.getLocation().x,changeBlinky.getLocation().y + 2));
												changeBlinky.setAngle(ghostDirections.get(generator.nextInt(3)));
												if(changeBlinky.getAngle()== 180){
													changeBlinky.setVector(new MyVector(-2,0));
												}
												else if(changeBlinky.getAngle()== 0){
													changeBlinky.setVector(new MyVector(2,0));
												}
												else if(changeBlinky.getAngle()== 90){
													changeBlinky.setVector(new MyVector(0,2));
												}
											}
											else if(changeBlinky.getAngle() == 90){
												changeBlinky.setLocation(new Point(changeBlinky.getLocation().x,changeBlinky.getLocation().y - 2));
												changeBlinky.setAngle(ghostDirections2.get(generator.nextInt(3)));
												if(changeBlinky.getAngle()== 180){
													changeBlinky.setVector(new MyVector(-2,0));
												}
												else if(changeBlinky.getAngle()== 270){
													changeBlinky.setVector(new MyVector(0,-2));
												}
												else if(changeBlinky.getAngle()== 0){
													changeBlinky.setVector(new MyVector(2,0));
												}
											}
											else if(changeBlinky.getAngle() == 0){
												changeBlinky.setLocation(new Point(changeBlinky.getLocation().x - 2,changeBlinky.getLocation().y));
												changeBlinky.setAngle(ghostDirections3.get(generator.nextInt(3)));
												if(changeBlinky.getAngle()== 270){
													changeBlinky.setVector(new MyVector(0,-2));
												}
												else if(changeBlinky.getAngle()== 90){
													changeBlinky.setVector(new MyVector(0,2));
												}
												else if(changeBlinky.getAngle()== 180){
													changeBlinky.setVector(new MyVector(-2,0));
												}
											}
											else if(changeBlinky.getAngle() == 180){
												changeBlinky.setLocation(new Point(changeBlinky.getLocation().x + 2,changeBlinky.getLocation().y ));
												changeBlinky.setAngle(ghostDirections4.get(generator.nextInt(3)));
												if(changeBlinky.getAngle()== 270){
													changeBlinky.setVector(new MyVector(0,-2));
												}
												else if(changeBlinky.getAngle()== 0){
													changeBlinky.setVector(new MyVector(2,0));
												}
												else if(changeBlinky.getAngle()== 90){
													changeBlinky.setVector(new MyVector(0,2));
												}
											}
										}
										else if( movingObj == changePinky){
											changePinky.setVector(new MyVector(0,0));
											if(changePinky.getAngle() == 270){
												changePinky.setLocation(new Point(changePinky.getLocation().x,changePinky.getLocation().y + 2));
												changePinky.setAngle(ghostDirections.get(generator.nextInt(3)));
												if(changePinky.getAngle()== 180){
													changePinky.setVector(new MyVector(-2,0));
												}
												else if(changePinky.getAngle()== 0){
													changePinky.setVector(new MyVector(2,0));
												}
												else if(changePinky.getAngle()== 90){
													changePinky.setVector(new MyVector(0,2));
												}
											}
											else if(changePinky.getAngle() == 90){
												changePinky.setLocation(new Point(changePinky.getLocation().x,changePinky.getLocation().y - 2));
												changePinky.setAngle(ghostDirections2.get(generator.nextInt(3)));
												if(changePinky.getAngle()== 180){
													changePinky.setVector(new MyVector(-2,0));
												}
												else if(changePinky.getAngle()== 270){
													changePinky.setVector(new MyVector(0,-2));
												}
												else if(changePinky.getAngle()== 0){
													changePinky.setVector(new MyVector(2,0));
												}
											}
											else if(changePinky.getAngle() == 0){
												changePinky.setLocation(new Point(changePinky.getLocation().x - 2,changePinky.getLocation().y));
												changePinky.setAngle(ghostDirections3.get(generator.nextInt(3)));
												if(changePinky.getAngle()== 270){
													changePinky.setVector(new MyVector(0,-2));
												}
												else if(changePinky.getAngle()== 90){
													changePinky.setVector(new MyVector(0,2));
												}
												else if(changePinky.getAngle()== 180){
													changePinky.setVector(new MyVector(-2,0));
												}
											}
											else if(changePinky.getAngle() == 180){
												changePinky.setLocation(new Point(changePinky.getLocation().x + 2,changePinky.getLocation().y ));
												changePinky.setAngle(ghostDirections4.get(generator.nextInt(3)));
												if(changePinky.getAngle()== 270){
													changePinky.setVector(new MyVector(0,-2));
												}
												else if(changePinky.getAngle()== 0){
													changeBlinky.setVector(new MyVector(2,0));
												}
												else if(changePinky.getAngle()== 90){
													changePinky.setVector(new MyVector(0,2));
												}
											}
										}
										if( movingObj == changeInky){
											changeInky.setVector(new MyVector(0,0));
											if(changeInky.getAngle() == 270){
												changeInky.setLocation(new Point(changeInky.getLocation().x,changeInky.getLocation().y + 2));
												changeInky.setAngle(ghostDirections.get(generator.nextInt(3)));
												if(changeInky.getAngle()== 180){
													changeInky.setVector(new MyVector(-2,0));
												}
												else if(changeInky.getAngle()== 0){
													changeInky.setVector(new MyVector(2,0));
												}
												else if(changeInky.getAngle()== 90){
													changeInky.setVector(new MyVector(0,2));
												}
											}
											else if(changeInky.getAngle() == 90){
												changeInky.setLocation(new Point(changeInky.getLocation().x,changeInky.getLocation().y - 2));
												changeInky.setAngle(ghostDirections2.get(generator.nextInt(3)));
												if(changeInky.getAngle()== 180){
													changeInky.setVector(new MyVector(-2,0));
												}
												else if(changeInky.getAngle()== 270){
													changeInky.setVector(new MyVector(0,-2));
												}
												else if(changeInky.getAngle()== 0){
													changeInky.setVector(new MyVector(2,0));
												}
											}
											else if(changeInky.getAngle() == 0){
												changeInky.setLocation(new Point(changeInky.getLocation().x - 2,changeInky.getLocation().y));
												changeInky.setAngle(ghostDirections3.get(generator.nextInt(3)));
												if(changeInky.getAngle()== 270){
													changeInky.setVector(new MyVector(0,-2));
												}
												else if(changeInky.getAngle()== 90){
													changeInky.setVector(new MyVector(0,2));
												}
												else if(changeInky.getAngle()== 180){
													changeInky.setVector(new MyVector(-2,0));
												}
											}
											else if(changeInky.getAngle() == 180){
												changeInky.setLocation(new Point(changeInky.getLocation().x + 2,changeInky.getLocation().y ));
												changeInky.setAngle(ghostDirections4.get(generator.nextInt(3)));
												if(changeInky.getAngle()== 270){
													changeInky.setVector(new MyVector(0,-2));
												}
												else if(changeInky.getAngle()== 0){
													changeInky.setVector(new MyVector(2,0));
												}
												else if(changeInky.getAngle()== 90){
													changeInky.setVector(new MyVector(0,2));
												}
											}
										}
										if( movingObj == changeClyde){
											changeClyde.setVector(new MyVector(0,0));
											if(changeClyde.getAngle() == 270){
												changeClyde.setLocation(new Point(changeClyde.getLocation().x,changeClyde.getLocation().y + 2));
												changeClyde.setAngle(ghostDirections.get(generator.nextInt(3)));
												if(changeClyde.getAngle()== 180){
													changeClyde.setVector(new MyVector(-2,0));
												}
												else if(changeClyde.getAngle()== 0){
													changeClyde.setVector(new MyVector(2,0));
												}
												else if(changeClyde.getAngle()== 90){
													changeClyde.setVector(new MyVector(0,2));
												}
											}
											else if(changeClyde.getAngle() == 90){
												changeClyde.setLocation(new Point(changeClyde.getLocation().x,changeClyde.getLocation().y - 2));
												changeClyde.setAngle(ghostDirections2.get(generator.nextInt(3)));
												if(changeClyde.getAngle()== 180){
													changeClyde.setVector(new MyVector(-2,0));
												}
												else if(changeClyde.getAngle()== 270){
													changeClyde.setVector(new MyVector(0,-2));
												}
												else if(changeClyde.getAngle()== 0){
													changeClyde.setVector(new MyVector(2,0));
												}
											}
											else if(changeClyde.getAngle() == 0){
												changeClyde.setLocation(new Point(changeClyde.getLocation().x - 2,changeClyde.getLocation().y));
												changeClyde.setAngle(ghostDirections3.get(generator.nextInt(3)));
												if(changeClyde.getAngle()== 270){
													changeClyde.setVector(new MyVector(0,-2));
												}
												else if(changeClyde.getAngle()== 90){
													changeClyde.setVector(new MyVector(0,2));
												}
												else if(changeBlinky.getAngle()== 180){
													changeBlinky.setVector(new MyVector(-2,0));
												}
											}
											else if(changeClyde.getAngle() == 180){
												changeClyde.setLocation(new Point(changeClyde.getLocation().x + 2,changeClyde.getLocation().y ));
												changeClyde.setAngle(ghostDirections4.get(generator.nextInt(3)));
												if(changeClyde.getAngle()== 270){
													changeClyde.setVector(new MyVector(0,-2));
												}
												else if(changeClyde.getAngle()== 0){
													changeClyde.setVector(new MyVector(2,0));
												}
												else if(changeClyde.getAngle()== 90){
													changeClyde.setVector(new MyVector(0,2));
												}
											}
										}
									}
									
									else if(movingObj instanceof Pacman && moving2 instanceof GhostChange){
										if(moving2 == changeClyde){
										eyesClyde = new Eyes(changeClyde.getLocation(), changeClyde.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesClyde);
										try {
											AudioClip ghostChomp=Applet.newAudioClip(new URL("file:pacman_eatghost.wav"));
											ghostChomp.play();
										} catch (MalformedURLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										screenObjects.remove(changeClyde);
										score +=1000;
										}
										else if(moving2 == changePinky){
										eyesPinky = new Eyes(changePinky.getLocation(), changePinky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesPinky);
										try {
											AudioClip ghostChomp=Applet.newAudioClip(new URL("file:pacman_eatghost.wav"));
											ghostChomp.play();
										} catch (MalformedURLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										screenObjects.remove(changePinky);
										score += 1000;
										}
										else if(moving2 == changeBlinky){
										eyesBlinky = new Eyes(changeBlinky.getLocation(), changeBlinky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesBlinky);
										try {
											AudioClip ghostChomp=Applet.newAudioClip(new URL("file:pacman_eatghost.wav"));
											ghostChomp.play();
										} catch (MalformedURLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										screenObjects.remove(changeBlinky);
										score +=1000;
										}
										else if(moving2 == changeInky){
										eyesInky = new Eyes(changeInky.getLocation(), changeInky.getSize(), 200, eyesImg.getImage());
										screenObjects.add(eyesInky);
										try {
											AudioClip ghostChomp=Applet.newAudioClip(new URL("file:pacman_eatghost.wav"));
											ghostChomp.play();
										} catch (MalformedURLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										screenObjects.remove(changeInky);
										score+=1000;
										}
									}
								}
							}
						}
					}
				}
				double dx = pac.getVector().getChangeX();
				double dy = pac.getVector().getChangeY();
				pac.getVector().setChangeX(dx);
				pac.getVector().setChangeY(dy);
										
			}
			if(Lives == 0){
				timer.stop();
				int xpac = screenWidth/2 - 10;
				int ypac = screenHeight/2 + 30;
				GameOver fail = new GameOver(new Point(0, 0),
						new Rectangle(560,620), 0,
						failure.getImage());
				screenObjects.add(fail);
				
				try {
					AudioClip failure=Applet.newAudioClip(new URL("file:pacman_intermission.wav"));
					failure.play();
				} catch (MalformedURLException e1) {

					e1.printStackTrace();
				}

			}
			boolean powerpelletsFound = false;
			boolean pacfoodFound = false; 
			for(ScreenObject obj : screenObjects){
				if (obj instanceof PowerPellet){
					powerpelletsFound = true;
				}
				else if (obj instanceof pacfood){
					pacfoodFound = true;
				}
			}
			if(!powerpelletsFound && !pacfoodFound){
				timer.stop();
				GameOver Victory = new GameOver(new Point(0, 0),
						new Rectangle(560,620), 0,
						victory.getImage());
				screenObjects.add(Victory);
						try {
							AudioClip failure=Applet.newAudioClip(new URL("file:pacman_intermission.wav"));
							failure.play();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
			}

			
			
			repaint();
			
		}
		
	}

	@Override
	/**
	 * This method defines how Pacman moves when the arrow keys are pressed. It changes Pacman's
	 * vector and angle based the on the key pressed.
	 * @param event 
	 */
	public void keyPressed(KeyEvent event) {
		   int keyCode = event.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            // handle up 
		        	pac.setAngle(270);
		        	pac.setVector(new MyVector(0,-2));

		        	break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	pac.setAngle(90);
		        	pac.setVector(new MyVector(0,2));
					
		            break;
		        case KeyEvent.VK_LEFT:
		        	pac.setAngle(180);
		        	pac.setVector(new MyVector(-2,0));
		            
		            break;
		        case KeyEvent.VK_RIGHT :
		        	pac.setAngle(0);
		        	pac.setVector(new MyVector(2,0));
		            
		            break;
		       
		     }
		    repaint();
		    
		
	}

	@Override
	/**
	 *This is a class that handles key released events.
	 *@param arg0 key event
	 */
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/*
	 * This is a class that handles the key typed events.
	 * @param arg0 key event
	 */
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}