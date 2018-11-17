package lookandsay;

import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger> {
  private BigInteger seed;
  private BigInteger nextNumber;
  private BigInteger prevNumber;
  private BigInteger endValue;

  /**
   * A Constructor that offers two arguments, a starting seed and an end value. Seed is the number
   * at which the iterator must begin. Iterator will stop once number greater than end is reached.
   *
   * @param seed     BigInteger that represents the number at which the iterator must begin.
   * @param endValue BigInteger that represents the number at which the iterator cannot go any
   *                 further.
   * @throws IllegalArgumentException if Argument passed is null, negative or greater than the
   *                                  largest hundred digit number.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger endValue) {
    checkArgs(seed);
    checkArgs(endValue);
    if (seed.compareTo(endValue) > 0) {
      throw new IllegalArgumentException("Seed cannot be greater than end value");
    }
    this.seed = seed;
    this.endValue = endValue;
  }

  /**
   * A Constructor that offers one argument, a starting seed. Seed is the number at which the
   * iterator must begin. Iterator will stop once number greater than end is reached. The end number
   * in this case is the biggest hundred digit number.
   *
   * @param seed BigInteger that represents the number at which the iterator must begin.
   * @throws IllegalArgumentException if Argument passed is null, negative or greater than the
   *                                  largest hundred digit number.
   */
  public LookAndSayIterator(BigInteger seed) {
    BigInteger temp = generateBiggestHundredDigitNumber();
    checkArgs(seed);
    this.seed = seed;
    this.endValue = temp;
  }

  /**
   * A Constructor that offers no arguments. Starting seed for these objects are set to 1 and the
   * end value is set to the biggest 100 digit number.
   */
  public LookAndSayIterator() {
    this.seed = new BigInteger(String.valueOf(1));
    this.endValue = generateBiggestHundredDigitNumber();
  }

  /**
   * Helper method to return the Biggest Hundred Digit Number.
   *
   * @return BigInteger that is the biggest hundred digit number.
   */
  private BigInteger generateBiggestHundredDigitNumber() {
    String temp = "";
    for (int i = 0; i < 100; i++) {
      temp = temp.concat("9");
    }
    return new BigInteger(temp);
  }

  /**
   * Helper function to check if the argument passed is not null, negative and lesser than the
   * largest hundred digit number.
   *
   * @throws IllegalArgumentException if Argument passed is null, negative or greater than the
   *                                  largest hundred digit number.
   */
  private void checkArgs(BigInteger toCheck) {
    BigInteger temp = generateBiggestHundredDigitNumber();
    if (toCheck == null) {
      throw new IllegalArgumentException("Illegal Arguments passed");
    }
    if (toCheck.toString().charAt(0) == '-') {
      throw new IllegalArgumentException("Illegal Arguments passed");
    }
    if (toCheck.compareTo(temp) > 0) {
      throw new IllegalArgumentException("Illegal Arguments passed");
    }
  }

  /**
   * Helper method to calculate the previous number in the look and say sequence for the argument
   * passed.
   *
   * @param bigInteger the value to which previous in the sequence is to be calculated.
   * @return bigInteger that is the previous in the sequence.
   * @throws IllegalStateException if the length of the argument passed is odd or if the value
   *                               passed is greater than the endValue.
   */
  private BigInteger calculatePreviousNumber(BigInteger bigInteger) {
    if (bigInteger.compareTo(this.endValue) > 0) {
      throw new IllegalStateException("Next of current not allowed");
    }
    String current = bigInteger.toString();
    String previous = "";
    if (current.length() % 2 != 0) {
      throw new IllegalStateException("Cannot calculate prev of number");
    }
    for (int i = 0; i < current.length(); i = i + 2) {
      char count = current.charAt(i);
      char value = current.charAt(i + 1);
      if (Character.getNumericValue(count) < 1) {
        throw new IllegalStateException("Illegal count");
      }
      int j = 0;
      while (j < Character.getNumericValue(count)) {
        previous = previous.concat(Character.toString(value));
        j++;
      }
    }
    BigInteger toReturn = new BigInteger(previous);
    return toReturn;
  }

  /**
   * Returns the previous element in the iteration.
   *
   * @return the previous element in the iteration.
   */
  @Override
  public BigInteger prev() {
    BigInteger toReturn = this.seed;
    if (this.hasPrevious()) {
      this.seed = this.prevNumber;
      this.prevNumber = new BigInteger("0");
    }
    return toReturn;
  }

  /**
   * Interface that represents reverse iterator operations. Has two functions, one that returns the
   * previous element of the collection that is being iterated and one that returns if there is such
   * an element. It also extends the Iterator interface, so any implementation of this interface
   * must override those methods.
   */
  @Override
  public boolean hasPrevious() {
    try {
      this.prevNumber = calculatePreviousNumber(this.seed);
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }

  /**
   * Helper method to calculate the next number in the look and say sequence for the argument
   * passed.
   *
   * @param bigInteger the value to which next in the sequence is to be calculated.
   * @return bigInteger that is the next in the sequence.
   * @throws IllegalStateException if the value passed is greater than the endValue.
   */
  private BigInteger calculateNextNumber(BigInteger bigInteger) {
    if (bigInteger.compareTo(this.endValue) > 0) {
      throw new IllegalStateException("Next of current not allowed");
    }
    String current = bigInteger.toString();
    String next = "";
    for (int i = 0; i < current.length(); i++) {
      int count = 1;
      int j = i + 1;
      while (true) {
        if (j < current.length() && current.charAt(j) == current.charAt(i)) {
          count++;
          j++;
        } else {
          next = next.concat(String.valueOf(count) + current.charAt(i));
          i = j - 1;
          break;
        }
      }
    }
    BigInteger toReturn = new BigInteger(next);
    return toReturn;
  }

  /**
   * Returns true if the next number to be returned is still lesser than end, false otherwise.
   *
   * @return true if the next number to be returned is still lesser than end, false otherwise.
   */
  @Override
  public boolean hasNext() {
    try {
      this.nextNumber = calculateNextNumber(this.seed);
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }

  /**
   * Returns the current number in the sequence and reverts to the previous number in the sequence.
   *
   * @return BigInteger that represents the current number in the sequence.
   */
  @Override
  public BigInteger next() {
    BigInteger toReturn = this.seed;
    if (this.hasNext()) {
      this.seed = this.nextNumber;
      this.nextNumber = new BigInteger("0");
    }
    return toReturn;
  }
}
