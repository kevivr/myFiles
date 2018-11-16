package vehicle;

/**
 * This interface represents a set of operations on any vehicle with manual transmission. A vehicle
 * with manual transmission is one in which the driver is required to change gears as he/she is
 * changing speed.
 */
public interface ManualTransmission {
  /**
   * Return the status of the transmission as a formatted string. The format of the string is as
   * follows:
   * <ul>
   * <li>OK: everything is OK.: this is the status if the speed was changed successfully without
   * changing gears, or the gear was changed successfully without changing speed.</li>
   * <li>OK: you may increase the gear.: this is the status if the speed was increased
   * successfully, but it is now within the range of the next gear.</li>
   * <li>OK: you may decrease the gear.: this is the status if the speed was decreased
   * successfully, but it is now within the range of the previous gear.</li>
   * <li>Cannot increase speed, increase gear first.: this is the status if the speed cannot be
   * increased more unless the gear is increased first. This implies that the intended speed is too
   * high for the current gear.</li>
   * <li>Cannot decrease speed, decrease gear first.: this is the status if the speed cannot be
   * decreased more unless the gear is decreased first. This implies that the intended speed is too
   * low for the current gear.</li>
   * <li>Cannot increase gear, increase speed first.: this is the status if the gear cannot be
   * increased more unless the speed is increased first. This implies that the current speed will be
   * too low for the next gear.</li>
   * <li>Cannot decrease gear, decrease speed first.: this is the status if the gear cannot be
   * decreased more unless the speed is decreased first. This implies that the current speed will be
   * too high for the previous gear.</li>
   * <li>Cannot increase speed. Reached maximum speed.: this is the status if the speed cannot be
   * increased as it will go beyond the speed limit of the vehicle.</li>
   * <li>Cannot decrease speed. Reached minimum speed.: this is the status if the speed cannot be
   * decreased as it is already 0.</li>
   * <li>Cannot increase gear. Reached maximum gear.: this is the status if the gear cannot be
   * increased as it is already in gear 5.</li>
   * <li>Cannot decrease gear. Reached minimum gear.: this is the status if the gear cannot be
   * decreased as it is already in gear 1.</li>
   * </ul>
   */
  String getStatus();

  /**
   * Return the current speed of the vehicle as a whole number.
   */
  int getSpeed();

  /**
   * Return the current gear of the vehicle as a whole number.
   */
  int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be increased, then return an object with the same
   * speed as before.
   */
  ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be decreased, then return an object with the same
   * speed as before.
   */
  ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be increased, then return an object with the same gear as before.
   */
  ManualTransmission increaseGear();

  /**
   * Decrease the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be decreased, then return an object with the same gear as before.
   */
  ManualTransmission decreaseGear();
}
