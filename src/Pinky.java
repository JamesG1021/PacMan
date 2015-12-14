import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a pinky ghost object
 * @author Joshua
 *
 */
public class Pinky extends Enemy {

	/**
	 * This initializes the location, size, point value and image for the pinky object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Pinky(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
	}

}
