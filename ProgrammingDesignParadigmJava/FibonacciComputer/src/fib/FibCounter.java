package fib;

/**
 * This class represents a Fibonacci counter. A Fibonacci counter  is a machine that counts
 * Fibonacci numbers. The count starts at 1 and there is no count below 1.
 */
public class FibCounter implements FibonacciCounter {
  /**
   * Declare class member to keep track of the count.
   */
  private int count;

  /**
   * Creates a FibCounter object and initialises the count to 1.
   */
  public FibCounter() {
    count = 1;
  }

  /**
   * Return the FibonacciCounter object with count incremented by 1.
   *
   * @return FibonacciCounter object with count incremented by 1.
   */
  public FibonacciCounter incrementFibonacciCounter() {
    this.count++;
    return this;
  }

  /**
   * Return the FibonacciCounter object with count decremented by 1.
   *
   * @return FibonacciCounter object with count decremented by 1.
   * @throws IndexOutOfBoundsException when count is equal to 1.
   */
  public FibonacciCounter decrementFibonacciCounter() throws RuntimeException {
    if (this.count == 1) {
      throw new RuntimeException("Count cannot  go below 1");
    }
    this.count--;
    return this;
  }

  /**
   * Return the current count of the FibonacciCounter object.
   *
   * @return the current count of the object.
   */
  public int getCount() {
    return this.count;
  }

  /**
   * Returns the number corresponding to the current count in this FibonacciCounter object.
   *
   * @return the number corresponding to the current count in this FibonacciCounter object.
   */
  public int getNumber() throws RuntimeException {
    if (this.count >= 48) {
      throw new RuntimeException("Number out of allowed int range");
    }
    if (this.count == 1) {
      return 0;
    } else if (this.count == 2) {
      return 1;
    } else {
      int temp1 = 0;
      int temp2 = 1;
      int result = 0;
      for (int i = 3; i <= count; i++) {
        result = temp1 + temp2;
        temp1 = temp2;
        temp2 = result;
      }
      return result;
    }

  }
}