package questions;

/**
 * This interface contains all operations that all types of questions should support.
 */
public interface Question extends Comparable<Question> {
  /**
   * Returns a String which depicts the evaluation of the answer String.
   *
   * @return "Correct" if the answer is correct and "Incorrect" otherwise.
   */
  String evaluateAnswer();

  /**
   * Sets the inp string to the String passed as a parameter to this function.
   */
  Question input(String inp);

  /**
   * Returns a String that is displayable as a Question.
   *
   * @return String Question along with Options String displayable as a Question.
   */
  String getQuestion();
}
