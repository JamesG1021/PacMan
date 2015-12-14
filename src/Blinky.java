import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a blinky ghost object
 * @author Joshua Lawson
 *
 */
public class Blinky extends Enemy {

	/**
	 * This initializes the location, size, point value and image for the blinky object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Blinky(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
	}

}
