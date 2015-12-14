import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates an eye object
 * @author Joshua
 *
 */
public class Eyes extends MovingScreenObject {

	/**
	 * This initializes the location, size, point value and image for the eye object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Eyes(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
		maximumage = 150; 
	}

}
