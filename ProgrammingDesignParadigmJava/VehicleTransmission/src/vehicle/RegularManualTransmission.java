package vehicle;

import java.util.Hashtable;

/**
 * This class represents a regular manual transmission vehicle which is an implementation of the
 * manual transmission interface. Regular Manual transmission allows for a speed change of 1 unit at
 * a time and has 5 gears.
 */
public class RegularManualTransmission implements ManualTransmission {
  /**
   * Declare class members to track.
   * <ul>
   * <li>current speed of the vehicle.</li>
   * <li>current Gear of the vehicle.</li>
   * <li>Lowest and Highest speeds of the 5 Gears in the vehicle.</li>
   * <li>Lowest Speed of the vehicle.</li>
   * <li>Highest Speed of the vehicle.</li>
   * <li>Status of the current Transmission.</li>
   * <li>A Hashtable that maps the different Gears to their highest and lowest speeds.</li>
   * </ul>
   */
  private int currentSpeed = 0;
  private Integer currentGear = 1;
  private String status = "OK: everything is OK.";
  private final int lowestSpeed = 0;
  private final int highestSpeed;
  private Hashtable<Integer, Gear> gears = new Hashtable<Integer, Gear>();

  /**
   * Constructs a Regular Manual Transmission Object ,initializes the highest speed of the vehicle
   * and the five gears based on the different values passed.
   *
   * @param low1  the lowest speed for the first Gear.
   * @param high1 the highest speed for the first Gear.
   * @param low2  the lowest speed for the second Gear.
   * @param high2 the highest speed for the second Gear.
   * @param low3  the lowest speed for the third Gear.
   * @param high3 the highest speed for the third Gear.
   * @param low4  the lowest speed for the fourth Gear.
   * @param high4 the highest speed for the fourth Gear.
   * @param low5  the lowest speed for the fifth Gear.
   * @param high5 the highest speed for the fifth Gear and the vehicle itself.
   */
  public RegularManualTransmission(int low1, int high1, int low2, int high2, int low3, int high3,
                                   int low4, int high4, int low5, int high5)
          throws IllegalArgumentException {
    if (rangeCheck(low1, high1, low2, high2, low3, high3, low4, high4, low5, high5)) {
      throw new IllegalArgumentException("Wrong speed Range.");
    }
    final Gear firstGear;
    final Gear secondGear;
    final Gear thirdGear;




    final Gear fourthGear;
    final Gear fifthGear;
    firstGear = new Gear(low1, high1);
    secondGear = new Gear(low2, high2);
    thirdGear = new Gear(low3, high3);
    fourthGear = new Gear(low4, high4);
    fifthGear = new Gear(low5, high5);
    this.highestSpeed = high5;
    this.gears.put(1, firstGear);
    this.gears.put(2, secondGear);
    this.gears.put(3, thirdGear);
    this.gears.put(4, fourthGear);
    this.gears.put(5, fifthGear);
  }

  private boolean rangeCheck(int low1, int high1, int low2, int high2,
                             int low3, int high3, int low4, int high4,
                             int low5, int high5) {
    if (low1 != 0) {
      return true;
    }
    if (low1 > high1 || low2 > high2 || low3 > high3 || low4 > high4 || low5 > high5) {
      return true;
    }
    if (low2 <= low1 || low3 <= low2 || low4 <= low3 || low5 <= low4) {
      return true;
    }
    if (low3 <= high1 || low4 <= high2 || low5 <= high3) {
      return true;
    }
    return (low2 > high1 || low3 > high2 || low4 > high3 || low5 > high4);

  }

  /**
   * Get the state of the transmission as a formatted string as defined in the interface.
   *
   * @return String that depicts the state of the current transmission.
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Get the current speed of the vehicle.
   *
   * @return Current speed of the vehicle.
   */
  public int getSpeed() {
    return this.currentSpeed;
  }

  /**
   * Get the current Gear of the vehicle.
   *
   * @return Current Gear of the vehicle.
   */
  public int getGear() {
    return this.currentGear;
  }

  /**
   * Increase the speed by 1 without changing gears and return the resulting transmission object. If
   * the speed cannot be increased, then return the object with speed unchanged.
   */
  public ManualTransmission increaseSpeed() {
    if (this.currentSpeed + 1 > this.highestSpeed) {
      this.status = "Cannot increase speed. Reached maximum speed.";
    } else if (this.currentSpeed + 1 <= this.gears.get(this.currentGear).getHighSpeed()) {
      this.currentSpeed += 1;
      if (this.currentGear != 5 && (this.currentSpeed >=
              this.gears.get(this.currentGear + 1).getLowSpeed())) {
        this.status = "OK: you may increase the gear.";
      } else {
        this.status = "OK: everything is OK.";
      }
    } else if (this.currentSpeed + 1 > this.gears.get(this.currentGear).getHighSpeed()) {
      this.status = "Cannot increase speed, increase gear first.";
    }
    return this;
  }

  /**
   * Decrease the speed by 1 without changing gears and return the resulting transmission object. If
   * the speed cannot be decreased, then return the object with speed unchanged.
   */
  public ManualTransmission decreaseSpeed() {
    if (this.currentSpeed - 1 < this.lowestSpeed) {
      this.status = "Cannot decrease speed. Reached minimum speed.";
    } else if (this.currentSpeed - 1 >= this.gears.get(this.currentGear).getLowSpeed()) {
      this.currentSpeed -= 1;
      if (this.currentGear != 1 &&
              (this.currentSpeed <= this.gears.get(this.currentGear - 1).getHighSpeed())) {
        this.status = "OK: you may decrease the gear.";
      } else {
        this.status = "OK: everything is OK.";
      }
    } else if (this.currentSpeed - 1 < this.gears.get(this.currentGear).getLowSpeed()) {
      this.status = "Cannot decrease speed, decrease gear first.";
    }
    return this;
  }

  /**
   * Increase the Gear by 1 without changing the speed and return the resulting transmission object.
   * If the gear cannot be changed, then return the object with same gear as before.
   */
  public ManualTransmission increaseGear() {
    if (this.currentGear == 5) {
      this.status = "Cannot increase gear. Reached maximum gear.";
    } else if (this.gears.get(this.currentGear + 1).getLowSpeed() <= this.currentSpeed) {
      this.currentGear += 1;
      this.status = "OK: everything is OK.";
    } else if (this.gears.get(this.currentGear + 1).getLowSpeed() > this.currentSpeed) {
      this.status = "Cannot increase gear, increase speed first.";
    }
    return this;
  }

  /**
   * Decrease the Gear by 1 without changing the speed and return the resulting transmission object.
   * If the gear cannot be changed, then return the object with same gear as before.
   */
  public ManualTransmission decreaseGear() {
    if (this.currentGear == 1) {
      this.status = "Cannot decrease gear. Reached minimum gear.";
    } else if (this.gears.get(this.currentGear - 1).getHighSpeed() >= this.currentSpeed) {
      this.currentGear -= 1;
      this.status = "OK: everything is OK.";
    } else if (this.gears.get(this.currentGear - 1).getHighSpeed() < this.currentSpeed) {
      this.status = "Cannot decrease gear, decrease speed first.";
    }
    return this;
  }
}

