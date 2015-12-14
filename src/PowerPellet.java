import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * 
 * This creates a power pellet object.
 * @author Joshua
 *
 */
public class PowerPellet extends MovingScreenObject {

	/**
	 * 
	 * This initializes the power pellet with the location, size, point value, and image
	 * @param location the starting location
	 * @param size the size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the power pellet
	 */
	public PowerPellet(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
	}

}
