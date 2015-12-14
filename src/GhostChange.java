import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates an object for the ghost change
 * @author Joshua
 *
 */
public class GhostChange extends MovingScreenObject {

	/**
	 * This initializes the location, size, point value and image for the ghost change
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public GhostChange(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
		maximumage = 300;
	}

}
