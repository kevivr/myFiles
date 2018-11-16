package fib;

/**
 * This interface represents a set of operations on a Fibonacci Counter.
 */
public interface FibonacciCounter {
  /**
   * Return the FibonacciCounter object with count incremented by 1.
   */
  FibonacciCounter incrementFibonacciCounter();

  /**
   * Return the FibonacciCounter object with count decremented by 1.
   */
  FibonacciCounter decrementFibonacciCounter();

  /**
   * Return the current count of the counter.
   */
  int getCount();

  /**
   * Return the Fibonacci number corresponding to the current count.
   */
  int getNumber();
}
