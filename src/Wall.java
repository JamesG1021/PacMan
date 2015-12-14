import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a wall object
 * @author Joshua
 *
 */
public class Wall extends MovingScreenObject {

	/**
	 * This initializes the location, size, point value, and image for the wall object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Wall(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
	}
}