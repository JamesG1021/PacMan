import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This creates a corall for the ghosts to start in
 * @author Jsg
 *
 */
public class Corral extends MovingScreenObject {
	/**
	 * This initializes the location, size, point value and image for the corall object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */

	public Corral(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
	}
	

}
