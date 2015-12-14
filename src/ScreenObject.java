import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * This creates a Screen Object to be displayed on the screen
 * @author Joshua Lawson
 *
 */
public abstract class ScreenObject {
	protected Point location;
	protected Rectangle size;
	
	/**
	 * Create the screen object with a particular location and size.
	 * @param location the starting location
	 * @param size size of the rectangle
	 */
	public ScreenObject(Point location, Rectangle size) {
		super();
		this.location = location;
		this.size = size;
	}

	/**
	 * Gets the location.
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * Gets the size of a rectangle.
	 * @return size the width and height of the rectangle.
	 */
	public Rectangle getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 * @param size the size to set
	 */
	public void setSize(Rectangle size) {
		this.size = size;
	}
	
	/**
	 * Abstract method for drawing
	 * @param g the graphics
	 */
	abstract public void draw(Graphics g);
		
}
