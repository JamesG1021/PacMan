import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * Creates an enemy that would be applied as an object in the Pacman game.
 * @author Joshua Lawson
 *
 */
public class Enemy extends MovingScreenObject {

	protected int pointValue;
	
	/**
	 * This initializes the location, size, point value and image for the enemy object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Enemy(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
		pointValue = pv;
		
	}

	/**
	 * Gets the point value of the enemy object.
	 * @return the pointValue
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * Sets the point value of an enemy object.
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	
}
