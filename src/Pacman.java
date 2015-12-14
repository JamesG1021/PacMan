import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * Creates the Pacman object.
 * @author Joshua
 *
 */
public class Pacman extends MovingScreenObject {
	
	private double direction;

	/**
	 * This initializes the location, size, point value and image for the pacman object
	 * @param location starting location
	 * @param size size of the rectangle
	 * @param pv point value
	 * @param i image assigned to the object
	 */
	public Pacman(Point location, Rectangle size, int pv, Image i) {
		super(location, size, pv, i);
		direction = 0;
	}
	/**
	 * Gets the direction Pacman is pointed.
	 * @return the direction
	 */
	public double getAngle() {
		return direction;
	}
	/**
	 * Sets Pacman's direction.
	 * @param direction the direction to set
	 */
	public void setAngle(double direction) {
		this.direction = direction;
	}

}