package vehicle;

/**
 * This class represents a gear. Each gear has a lowest speed and highest speed and can only
 * accommodate speeds within this range.
 */
public class Gear {
  /**
   * Declare members of the class that will represent the lowest and highest speeds the gear can
   * accommodate.
   */
  int lowSpeed;
  int highSpeed;

  public Gear(int lowSpeed, int highSpeed) {
    this.lowSpeed = lowSpeed;
    this.highSpeed = highSpeed;
  }

  /**
   * Get the lowest speed of this Gear.
   *
   * @return the lowest speed of this Gear.
   */
  public int getLowSpeed() {
    return this.lowSpeed;
  }

  /**
   * Get the highest speed of this Gear.
   *
   * @return the highest speed of this Gear.
   */
  public int getHighSpeed() {
    return this.highSpeed;
  }

}
