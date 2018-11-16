import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;


/**
 * A Junit test class for the RegularManualTransmission class that implements ManualTransmission
 * Interface.
 */
public class RegularManualTransmissionTest {
  private ManualTransmission honda;
  private ManualTransmission testVehicle;
  private ManualTransmission maruti;
  private RegularManualTransmission tata;

  /**
   * Construct sample Object to test RegularManualTransmission Class.
   */
  @Before
  public void setUp() {
    honda = new RegularManualTransmission(0, 3, 2, 5, 4, 7, 6, 9, 8, 11);
    testVehicle = new RegularManualTransmission(0, 1, 1, 2, 2, 3, 3, 4, 4, 5);
    //Maruti = new RegularManualTransmission(0, 10, 12, 20, 22, 30, 32, 40, 42, 50);
    //Tata = new RegularManualTransmission(1, 10, 12, 20, 22, 30, 32, 40, 42, 50);
  }

  /**
   * Test case to test creation of Regular Manual Transmission Object when passed with acceptable
   * parameter values.
   */
  @Test
  public void testNewTransmission() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 3, 2, 5, 4, 7, 6, 9, 8, 11);
    assertEquals(tV.getStatus(),
            "OK: everything is OK.");
  }

  /**
   * Test case to test getGear Function.
   */
  @Test
  public void testGetGear() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 3, 2, 5, 4, 7, 6, 9, 8, 11);
    assertEquals(tV.getGear(),
            1);
  }

  /**
   * Test case to test getSpeed Function.
   */
  @Test
  public void testGetSpeed() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 3, 2, 5, 4, 7, 6, 9, 8, 11);
    assertEquals(tV.getSpeed(),
            0);
  }

  /**
   * Test case to test when low 1 gear is not set to 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNewTransmissionLowGearZero() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(3, 6, 5, 8, 7, 10, 9, 12, 11, 14);
  }

  /**
   * Test case to test when low x gear is greater than high x gear.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNewTransmissionLowGearGreaterThanHighGear() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 6, 10, 9, 8, 11, 10, 13, 12, 15);
  }

  /**
   * Test case to test when the lowest speed of a higher range is lesser than or equal to the
   * lowest speed of a lower range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGearLowSpeedGreaterThanNextGearLowSpeed() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 7, 6, 9, 4, 10, 9, 13, 12, 20);
  }

  /**
   * Test case to test when non adjacent gears overlap.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonAdjacentGearsOverlap() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 4, 1, 5, 2, 6, 3, 7, 4, 8);
  }

  /**
   * Test case when adjacent gears are non overlapping.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAdjacentGearsNonOverlapping() {
    ManualTransmission tV;
    tV = new RegularManualTransmission(0, 4, 5, 8, 9, 10, 11, 15, 16, 21);
  }


  /**
   * Test case when vehicle has a Successful speed increase.
   */
  @Test
  public void testIncreaseSpeed() {
    assertEquals(honda.increaseSpeed().getStatus(),
            "OK: everything is OK.");
  }

  /**
   * Test case when vehicle has a successful speed increase and the gear is changeable.
   */
  @Test
  public void testIncreaseSpeedGearChangeable() {
    assertEquals(honda.increaseSpeed().increaseSpeed().getStatus(),
            "OK: you may increase the gear.");
  }

  /**
   * Test case when vehicle has an unsuccessful speed increase because gear needs to be increased.
   */
  @Test
  public void testIncreaseSpeedFail1() {
    assertEquals(honda.increaseSpeed().increaseSpeed().increaseSpeed().increaseSpeed().getStatus(),
            "Cannot increase speed, increase gear first.");
  }

  /**
   * Test case when vehicle has an unsuccessful speed increase because maximum speed limit has been
   * reached.
   */
  @Test
  public void testIncreaseSpeedFail2() {
    for (int i = 0; i < 4; i++) {
      testVehicle = testVehicle.increaseSpeed().increaseGear();
    }
    assertEquals(testVehicle.increaseSpeed().increaseSpeed().getStatus(),
            "Cannot increase speed. Reached maximum speed.");
  }

  /**
   * Test case when vehicle has an unsuccessful speed decrease because minimum speed limit has been
   * reached.
   */
  @Test
  public void testDecreaseSpeedFail2() {
    assertEquals(testVehicle.decreaseSpeed().getStatus(),
            "Cannot decrease speed. Reached minimum speed.");
  }

  /**
   * Test case when vehicle has an unsuccessful speed decrease because gear has to be decreased.
   */
  @Test
  public void testDecreaseSpeedFail1() {
    assertEquals(testVehicle.increaseSpeed().increaseGear().decreaseSpeed().getStatus(),
            "Cannot decrease speed, decrease gear first.");
  }

  /**
   * Test case when vehicle has a successful speed decrease and the gear is changeable.
   */
  @Test
  public void testDecreaseSpeedGearChangeable() {
    assertEquals(
            testVehicle.increaseSpeed().increaseGear().increaseSpeed().decreaseSpeed().getStatus(),
            "OK: you may decrease the gear.");
  }

  /**
   * Test case when vehicle has a Successful speed decrease.
   */
  @Test
  public void testDecreaseSpeed() {
    assertEquals(honda.increaseSpeed().increaseSpeed().decreaseSpeed().getStatus(),
            "OK: everything is OK.");
  }

  /**
   * Test case when vehicle has a successful gear increase.
   */
  @Test
  public void testIncreaseGear() {
    assertEquals(testVehicle.increaseSpeed().increaseGear().getStatus(), "OK: everything is OK.");
  }

  /**
   * Test case when vehicle cannot increase gear because reached maximum gear.
   */
  @Test
  public void testIncreaseGearFail1() {
    for (int i = 0; i < 5; i++) {
      testVehicle = testVehicle.increaseSpeed().increaseGear();
    }
    assertEquals(testVehicle.getStatus(), "Cannot increase gear. Reached maximum gear.");
  }

  /**
   * Test case when vehicle cannot increase gear because speed has to be increased first so the
   * vehicle speed is in the gear range.
   */
  @Test
  public void testIncreaseGearFail2() {
    testVehicle = testVehicle.increaseGear();
    assertEquals(testVehicle.getStatus(), "Cannot increase gear, increase speed first.");
  }

  /**
   * Test case when vehicle cannot decrease gear because speed has to be decreased first so the
   * vehicle speed is in the gear range.
   */
  @Test
  public void testDecreaseGearFail2() {
    testVehicle = testVehicle.increaseSpeed().increaseGear().increaseSpeed().decreaseGear();
    assertEquals(testVehicle.getStatus(), "Cannot decrease gear, decrease speed first.");
  }

  /**
   * Test case when vehicle cannot increase gear because reached minimum gear.
   */
  @Test
  public void testDecreaseGearFail1() {
    assertEquals(testVehicle.decreaseGear().getStatus(),
            "Cannot decrease gear. Reached minimum gear.");
  }

  /**
   * Test case when vehicle has a successful gear decrease.
   */
  @Test
  public void testDecreaseGear() {
    assertEquals(testVehicle.increaseSpeed().increaseGear().decreaseGear().getStatus(),
            "OK: everything is OK.");
  }

  /**
   * Test case when mixing increasing speed and decreasing speed.
   */
  @Test
  public void testSpeedMix() {
    ManualTransmission tV = new RegularManualTransmission(
            0, 10, 9, 19, 18, 38, 37, 57, 56, 66);
    assertEquals(tV.increaseSpeed().increaseSpeed().increaseSpeed()
            .decreaseSpeed().increaseSpeed().decreaseSpeed().decreaseSpeed()
            .increaseSpeed().decreaseSpeed().getSpeed(), 1);
  }

  /**
   * Test case when mixing incrementing and decrementing gear.
   */
  @Test
  public void testGearMix() {
    ManualTransmission tV = new RegularManualTransmission(
            0, 3, 2, 5, 4, 9, 6, 11, 10, 13
    );
    assertEquals(tV.increaseSpeed().increaseSpeed().increaseGear().increaseGear()
            .increaseSpeed().increaseSpeed().increaseGear().increaseSpeed().decreaseGear().
                     decreaseSpeed().decreaseSpeed().getGear(), 2);
  }

}
