/**
 * 
 */

/**
 * Deals with the vector of an object, the movement.
 * @author Joshua Lawson
 *
 */
public class MyVector {
	private double changeX;
	private	double changeY;
	/**
	 * Initializes the vector by the change in the x value and the change in the y value.
	 * @param changeX
	 * @param changeY
	 */
	public MyVector(double changeX, double changeY) {
		super();
		this.changeX = changeX;
		this.changeY = changeY;
	}
	/**
	 * Gets the change in x.
	 * @return the changeX
	 */
	public double getChangeX() {
		return changeX;
	}
	/**
	 * Sets the change in y.
	 * @param changeX the changeX to set
	 */
	public void setChangeX(double changeX) {
		this.changeX = changeX;
	}
	/**
	 * Get the change in y.
	 * @return the changeY
	 */
	public double getChangeY() {
		return changeY;
	}
	/**
	 * Set the change in y.
	 * @param changeY the changeY to set
	 */
	public void setChangeY(double changeY) {
		this.changeY = changeY;
	}
	
	
}
