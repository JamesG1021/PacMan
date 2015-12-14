import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


/**
 * The MovingScreenObject creates an object that will move on the Screen.
 * @author Joshua Lawson
 *
 */
public class MovingScreenObject extends ScreenObject {

	MyVector vector;
	Image myImage;
	protected int age;
	protected int maximumage;

	protected double angle;
	/**
	 * This initializes the location, size, point value and image for the moving screen object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public MovingScreenObject(Point location, Rectangle size, int pv, Image i) {
		super(location, size);
		vector = new MyVector(0,0);
		myImage = i;
		age = 0;
		maximumage = Integer.MAX_VALUE;
	
	}
	
	public void move() {
		
		age++;
		location.x += vector.getChangeX();
		location.y += vector.getChangeY();
		
		if (location.x > Screen.screenWidth) {
			location.x -= Screen.screenWidth;
		}
		if (location.x < 0) {
			location.x += Screen.screenWidth;
		}
		
		if (location.y > Screen.screenHeight) {
			location.y -= Screen.screenHeight;
		}
		if (location.y < 0) {
			location.y += Screen.screenHeight;
		}
		
	}
	
	/**
	 * Gets the age.
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the maximum age.
	 * @return the maximumage
	 */
	public int getMaximumage() {
		return maximumage;
	}

	/**
	 * Sets the maximum age.
	 * @param maximumage the maximumage to set
	 */
	public void setMaximumage(int maximumage) {
		this.maximumage = maximumage;
	}

	/**
	 * Gets the angle.
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * Sets the angle.
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Detects if the objects have collided.
	 * @param otherObj
	 * @return returns if rectangle1 intersects rectangle2
	 */
	public boolean collide(MovingScreenObject otherObj) {
		Rectangle rect1 = new Rectangle(location.x,location.y,size.width,size.height);
		Rectangle rect2 = new Rectangle(otherObj.location.x,otherObj.location.y,otherObj.size.width,otherObj.size.height);
		return rect1.intersects(rect2);
	}
	
	/**
	 * Deals with Pacman graphics alignment.
	 * @param g draws a screen object
	 */
	public void draw(Graphics g) {
		// reminder ... later we want to rotate image by
		// changeX and changeY
		Graphics2D g2 = (Graphics2D) g;
		if (this instanceof Pacman || this instanceof Pacman) {
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(location.x, location.y);
			trans.scale(0.25, 0.25);
	
			trans.rotate( Math.toRadians(this.getAngle()),
					myImage.getWidth(null)/2,
					myImage.getHeight(null)/2);


			g2.drawImage(myImage, trans, null);
		}
		else {
			g2.drawImage(myImage, location.x, location.y,
					size.width, size.height,
					null);
		}
	}

	/**
	 * Returns the vector.
	 * @return the vector
	 */
	public MyVector getVector() {
		return vector;
	}

	/**
	 * Sets the vector.
	 * @param vector the vector to set
	 */
	public void setVector(MyVector vector) {
		this.vector = vector;
	}

	/**
	 * Get the image of an object.
	 * @return the myImage
	 */
	public Image getMyImage() {
		return myImage;
	}

	/**
	 * Set the image of an object.
	 * @param myImage the myImage to set
	 */
	public void setMyImage(Image myImage) {
		this.myImage = myImage;
	}

}
