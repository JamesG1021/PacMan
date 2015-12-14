
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This creates a Fruit object
 * @author Taylor Benson
 *
 */
public class Fruit extends MovingScreenObject {
	/**
	 * This initializes the location, size, point value and image for the fruit object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	
	public Fruit (Point p, Rectangle s, int pv, Image i){
		super(p, s, pv, i);
	}
}

