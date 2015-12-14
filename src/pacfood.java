import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a pacfood object
 * @author Joshua
 *
 */
public class pacfood extends MovingScreenObject {

	/**
	 * This initializes the location, size, point value and image for moving screen object
	 * @param location the starting location
	 * @param size the size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public pacfood(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
	}

}
