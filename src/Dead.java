import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a dead object for when pacman dies
 * @author Joshua Lawson
 *
 */
public class Dead extends MovingScreenObject {

	/**
	 * This initializes the location, size, point value and image for the dead object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Dead(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
		maximumage = 15;
	}

}
