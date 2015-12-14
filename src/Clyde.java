import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a clyde ghost object
 * @author Joshua
 *
 */
public class Clyde extends Enemy {

	/**
	 * This initializes the location, size, point value and image for the clyde object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Clyde(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
	}

}
