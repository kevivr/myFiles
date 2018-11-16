package questions;

/**
 * This class represents a MultipleChoice type Question. These types of questions have only one
 * correct answer - one of the several options.
 */
public class MultipleChoice extends MultipleAnswer {
  /**
   * Construct a MultipleChoice Question object using the given question, options and answer.
   *
   * @param question question String
   * @param options  options String
   * @param answer   answer String
   */
  public MultipleChoice(String question, String options, String answer) {
    super(checkMultipleChoiceArguments(question, answer), options, answer);
  }

  /**
   * Checks if only one answer amongst the options is valid.
   *
   * @return question String.
   */
  private static String checkMultipleChoiceArguments(String question, String answer)
          throws IllegalArgumentException {
    if (answer.split(" ").length != 1) {
      throw new IllegalArgumentException("Multiple Choice questions "
              + "cannot have more than one answer");
    } else {
      return question;
    }
  }

  /**
   * CompareTo function comparing a MultipleChoice Question object with a YesNo Question object.
   *
   * @param other Question to be compared with.
   * @return the result of comparison.
   */
  @Override
  protected int compareToYesNo(AbstractQuestion other) {
    return -1;
  }

  /**
   * CompareTo function comparing a MultipleChoice Question object with a MultipleChoice Question
   * object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToMultipleChoice(AbstractQuestion other) {
    return other.question.compareTo(this.question);
  }

  /**
   * CompareTo function comparing a MultipleChoice Question object with a MultipleAnswer Question
   * object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToMultipleAnswer(AbstractQuestion other) {
    return 1;
  }

  /**
   * CompareTo function comparing a MultipleChoice Question object with a Likert Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToLikert(AbstractQuestion other) {
    return -1;
  }

  /**
   * CompareTo function to compare a MultipleChoice question with a Question object.
   *
   * @param o object of type Question.
   * @return the result of comparison.
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQn = (AbstractQuestion) o;
      return aQn.compareToMultipleChoice(this);
    }
    return 1;
  }
}
