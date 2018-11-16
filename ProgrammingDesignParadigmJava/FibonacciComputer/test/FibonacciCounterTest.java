import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fib.FibonacciCounter;
import fib.FibCounter;


/**
 * A Junit test class for the FibonacciCounter Interface.
 */
public class FibonacciCounterTest {
  /**
   * A Junit test to test FibCounter constructor.
   */
  @Test
  public void testFibonacciConstructor() {
    FibonacciCounter testCounter = new FibCounter();
    assertEquals(testCounter.getCount(), 1);
    assertEquals(testCounter.getNumber(), 0);
  }

  /**
   * A Junit test to test Increment counter function.
   */
  @Test
  public void testFibonacciIncrementCounter() {
    FibonacciCounter testCounter = new FibCounter();
    for (int i = 0; i < 10; i++) {
      testCounter = testCounter.incrementFibonacciCounter();
    }
    assertEquals(testCounter.getCount(), 11);
    assertEquals(testCounter.getNumber(), 55);
  }

  /**
   * A Junit test to test Decrement counter function.
   */
  @Test
  public void testFibonacciDecrementCounter() {
    FibonacciCounter testCounter = new FibCounter();
    for (int i = 0; i < 5; i++) {
      testCounter = testCounter.incrementFibonacciCounter();
    }
    for (int i = 0; i < 5; i++) {
      testCounter = testCounter.decrementFibonacciCounter();
    }
    assertEquals(testCounter.getCount(), 1);
    assertEquals(testCounter.getNumber(), 0);
  }

  /**
   * A Junit test to test the Runtime exception when attempting to decrement count below 1.
   */
  @Test(expected = RuntimeException.class)
  public void testDecrementCounterException() {
    FibonacciCounter testCounter = new FibCounter();
    testCounter.decrementFibonacciCounter();
  }

  /**
   * A Junit test to test the Runtime exception when attempting to get Fibonacci number for count
   * when the fibonacci number exceeds the allowable range for int.
   */
  @Test(expected = RuntimeException.class)
  public void testGetNumberException() {
    FibonacciCounter testCounter = new FibCounter();
    for (int i = 0; i < 47; i++) {
      testCounter = testCounter.incrementFibonacciCounter();
    }
    testCounter.getNumber();
  }
}
