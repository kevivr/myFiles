import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

/**
 * A JUnit test class for the SimpleProjectile Class that implements Particle Interface.
 */
public class SimpleProjectileTest {

  private SimpleProjectile particle;
  private SimpleProjectile particle2;
  private SimpleProjectile particle3;
  private SimpleProjectile particle4;
  private SimpleProjectile particle5;

  /**
   * Construct sample Object to test SimpleProjectile Class.
   */
  @Before
  public void setUp() {
    particle = new SimpleProjectile(0, 0, 10, 10);
    particle2 = new SimpleProjectile(-6, 3, 30, 15);
    particle3 = new SimpleProjectile(6.7f, -2.54f, 3.1f, 7.6f);
    particle4 = new SimpleProjectile(10, 10, 6, -7);
    particle5 = new SimpleProjectile(5, 7, -5, 32);
  }

  /**
   * Test case when particle has not fallen to the ground.
   */
  @Test
  public void testSimpleGetState() {
    assertEquals(particle.getState(1.0387f), "At time 1.04: position is (10.39,5.10)");
  }

  /**
   * Test case when particle has fallen to the ground.
   */
  @Test
  public void testNegativeFinalYGetState() {
    assertEquals(particle.getState(3.0387f), "At time 3.04: position is (20.39,0.00)");
  }

  /**
   * Test case when time is negative.
   */
  @Test
  public void testNegativeTimeGetState() {
    assertEquals(particle.getState(-9.88f), "At time -9.88: position is (0.00,0.00)");
  }

  /**
   * Test case when initial x coordinate is negative.
   */
  @Test
  public void testNegativeInitialXTimeGetState() {
    assertEquals(particle2.getState(2.034f), "At time 2.03: position is (55.02,13.22)");
  }

  /**
   * Test case when initial y coordinate is negative.
   */
  @Test
  public void testNegativeInitialYTimeGetState() {
    assertEquals(particle3.getState(2.03f), "At time 2.03: position is (11.50,-2.54)");
  }

  /**
   * Test case when initial y component of velocity is negative.
   */
  @Test
  public void testNegativeYVelocity() {
    assertEquals(particle4.getState(3.455f), "At time 3.45: position is (10.00,10.00)");
  }

  /**
   * Test case when initial x component of velocity is negative.
   */
  @Test
  public void testNegativeXVelocity() {
    assertEquals(particle5.getState(3.122f), "At time 3.12: position is (-10.61,59.10)");
  }
}