import java.text.DecimalFormat;

/**
 * This class represents a newtonian particle. A newtonian particle is a particle that obeys
 * Newton's laws of motion. A newtonian particle has an initial position represented by its x and y
 * coordinates. It will also have an initial velocity represented again by its x and y coordinates.
 */

public class SimpleProjectile implements Particle {
  private float xPosition;
  private float yPosition;
  private float xVelocity;
  private float yVelocity;

  /**
   * Constructs a Simple Projectile Object and initializes it to the given initial x and y
   * coordinates and x and y components of initial velocity.
   *
   * @param xPosition the x coordinate of the initial position of the particle.
   * @param yPosition the y coordinate of the initial position of the particle.
   * @param xVelocity the x component of the initial velocity of the particle.
   * @param yVelocity the y component of the initial velocity of the particle.
   */
  public SimpleProjectile(float xPosition, float yPosition, float xVelocity, float yVelocity) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
  }

  /**
   * Get the y coordinate of the position of this particle at given time.
   *
   * @return the y coordinate of the position of this particle at given time.
   */
  public float getYFinalPosition(float time) {
    return this.yPosition + (this.yVelocity * time - (0.50f * 9.81f * time * time));
  }

  /**
   * Get the x coordinate of the position of this particle at given time.
   *
   * @return the x coordinate of the position of this particle at given time.
   */
  public float getXFinalPosition(float time) {
    return this.xPosition + (this.xVelocity * time);
  }

  /**
   * Return the state of this particle as a formatted string. The format of the string is as
   * follows:
   * <code>At time [t]: position is ([x],[y])</code> where
   * <ul>
   * <li>[t] is the time passed to this method, rounded to three decimal
   * places</li>
   * <li>[x] is the x-coordinate of the position of this particle at this
   * time, rounded to three decimal places </li>
   * <li>[y] is the y-coordinate of the position of this particle at this
   * time, rounded to three decimal places
   * </li> </ul>
   *
   * @param time the time at which the state must be obtained
   * @return the state of the particle as a string formatted as above
   */
  public String getState(float time) {
    float xFinalPosition = getXFinalPosition(time);
    float yFinalPosition = getYFinalPosition(time);
    DecimalFormat df = new DecimalFormat("#0.00");
    if (time > 0) {
      if (yFinalPosition < this.yPosition) {
        if (this.yVelocity < 0) {
          xFinalPosition = this.xPosition;
        } else {
          xFinalPosition = getXFinalPosition(this.yVelocity / (0.50f * 9.81f));
        }
        return "At time " + df.format(time) + ": position is ("
                + df.format(xFinalPosition) + "," + df.format(this.yPosition) + ")";
      }
      return "At time " + df.format(time) + ": position is ("
              + df.format(xFinalPosition) + "," + df.format(yFinalPosition) + ")";
    } else {
      return "At time " + df.format(time) + ": position is ("
              + df.format(this.xPosition) + "," + df.format(this.yPosition) + ")";
    }
  }


}
