package calculator;

/**
 * This interface represents a calculator.
 */
public interface Calculator {
  /**
   * Gets Calculator object that contains the result of the processed input.
   *
   * @param inp the character that is appended to the input that is to be processed.
   * @return Calculator object which is a result of processing the input
   */
  Calculator input(char inp);

  /**
   * Gets result String which contains the processed input till then.
   *
   * @return String result which is the processed input till then.
   */
  String getResult();
}
