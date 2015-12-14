import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates the ghost Inky
 * @author Joshua
 *
 */
public class Inky extends Enemy {

	/**
	 * This initializes the location, size, point value and image of Inky
	 * @param p the starting location
	 * @param s the size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Inky(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
	}

}
