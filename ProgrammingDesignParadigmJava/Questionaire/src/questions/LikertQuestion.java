package questions;

/**
 * This class represents a Likert type Question. These types of questions can be answered on a fixed
 * 5-point Likert scale (Strongly Agree, Agree, Neither Agree nor Disagree, Disagree, Strongly
 * Disagree).
 */
public class LikertQuestion extends AbstractQuestion {

  /**
   * Construct a LikertQuestion object using the given question.
   *
   * @param question question String.
   */
  public LikertQuestion(String question) {
    super(checkArguments(question));
    this.options = optionsForLikert();
  }

  /**
   * This function checks if the arguments entered are valid, and if not throws an IllegalArgument
   * exception.
   *
   * @param question the question String.
   * @return question to be passed to super constructor if no exception is thrown.
   */
  private static String checkArguments(String question) throws IllegalArgumentException {
    if (question.equals("") || question == null) {
      throw new IllegalArgumentException("Question cannot be empty");
    }
    return question;
  }

  /**
   * This method checks the entered input is of the correct format.
   */
  @Override
  protected void checkAnswer() {
    if (!input.matches("^[1-5]$")) {
      this.result = evaluation.INCORRECT;
    } else {
      this.result = evaluation.CORRECT;
    }
  }

  /**
   * Method to create options for Likert type Question.
   *
   * @return Constructed string.
   */
  private String optionsForLikert() {
    return "1.Strongly Agree, 2.Agree, 3.Neither Agree or Disagree, "
            + "4.Disagree, 5.Strongly Disagree";
  }

  /**
   * CompareTo function comparing a Likert Question object with a YesNo Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToYesNo(AbstractQuestion other) {
    return -1;
  }

  /**
   * CompareTo function comparing a Likert Question object with a Likert Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToLikert(AbstractQuestion other) {
    return other.question.compareTo(this.question);
  }

  /**
   * CompareTo function to compare a Likert question with a Question object.
   *
   * @param o object of type Question.
   * @return the result of comparison.
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQn = (AbstractQuestion) o;
      return aQn.compareToLikert(this);
    }
    return 1;
  }
}
