import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This creates a Game Over screen object
 * @author Jsg
 *
 */
public class GameOver extends MovingScreenObject {
	/**
	 * This initializes the location, size, point value and image for the game over object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */

	public GameOver(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);

	}
	

}
