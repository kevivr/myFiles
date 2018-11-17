import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A Junit test class for the FreecellModel class that implements FreecellOperations Interface.
 */

public class LookAndSayTest {
  private RIterator<BigInteger> testValue;

  /**
   * Construct the sample object to be used in the test cases in this class.
   */
  @Before
  public void setUp() {
    testValue = new LookAndSayIterator(new BigInteger("1"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(null, new BigInteger("123456"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors2() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("123456"), null);
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors3() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(null, null);
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors4() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("-1"), new BigInteger("12345"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors5() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("12345"), new BigInteger("-1"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors6() {
    BigInteger temp = new BigInteger("50").pow(101);
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(temp, new BigInteger("12345"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors7() {
    BigInteger temp = new BigInteger("50").pow(101);
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("12345"), temp);
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors8() {
    BigInteger temp = new BigInteger("50").pow(101);
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(temp);
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors9() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("-1"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors11() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(null);
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructors12() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("123"), new BigInteger("12"));
  }

  /**
   * Test the different constructors of the LookAndSayIterator class.
   */
  @Test
  public void testConstructors13() {
    RIterator<BigInteger> firstObject =
            new LookAndSayIterator(new BigInteger("123"), new BigInteger("12234234234123"));
    RIterator<BigInteger> secondObject =
            new LookAndSayIterator(new BigInteger("123"));
    RIterator<BigInteger> thirdObject =
            new LookAndSayIterator();
    assertTrue(firstObject.hasNext());
    assertTrue(secondObject.hasNext());
    assertTrue(thirdObject.hasNext());
  }

  /**
   * Test the hasNext and hasPrevious function.
   */
  @Test
  public void testHas() {
    assertTrue(testValue.hasNext());
    assertFalse(testValue.hasPrevious());
  }

  /**
   * Test hasNext is false after value crosses the endvalue.
   */
  @Test
  public void testHasNextFalse() {
    BigInteger temp = new BigInteger("10").pow(100).subtract(new BigInteger("1"));
    while (testValue.hasNext()) {
      testValue.next();
    }
    assertEquals(new BigInteger("13211321322113311213211331121113122112132113121113" +
            "2221123113112221131112311332111213211322211312113211"), testValue.next());
    assertFalse(testValue.hasNext());
    assertTrue(testValue.next().compareTo(temp) > 0);
  }

  /**
   * Test hasNext is false after value crosses the endvalue.
   */
  @Test
  public void testHasNextFalse2() {
    RIterator<BigInteger> object1 =
            new LookAndSayIterator(new BigInteger("1"), new BigInteger("10000"));
    while (object1.hasNext()) {
      object1.next();
    }
    assertEquals(new BigInteger("111221"), object1.next());
    assertFalse(object1.hasNext());
    assertTrue(object1.next().compareTo(new BigInteger("10000")) > 0);
  }

  /**
   * Test hasNext, next, hasPrevious and prev for valid number.
   */
  @Test
  public void testValidNumber() {
    assertEquals(new BigInteger("1"), testValue.next());
    assertEquals(new BigInteger("11"), testValue.prev());
    assertEquals(new BigInteger("1"), testValue.prev());
  }

  /**
   * Test prev returns same number when reaches single digit value.
   */
  @Test
  public void testPrev() {
    RIterator<BigInteger> object1 =
            new LookAndSayIterator(new BigInteger("111221"), new BigInteger("100000000"));
    while (object1.hasPrevious()) {
      object1.prev();
    }
    assertEquals(new BigInteger("1"), testValue.prev());
    assertFalse(testValue.hasPrevious());
    assertTrue(testValue.next().compareTo(new BigInteger("0")) > 0);
  }

  /**
   * Test prev and next multiple times.
   */
  @Test
  public void testPrevNext() {
    for (int i = 0; i < 4; i++) {
      testValue.next();
    }
    for (int i = 0; i < 4; i++) {
      testValue.prev();
    }
    assertEquals(new BigInteger("1"), testValue.prev());
  }
}
