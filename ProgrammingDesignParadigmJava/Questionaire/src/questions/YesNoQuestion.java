package questions;

/**
 * This class represents a YesNo type Question. These types of questions can be answered in one of
 * two ways: "Yes" or "No".
 */
public class YesNoQuestion extends AbstractQuestion {
  /**
   * Construct a YesNoQuestion object using the given question and answer.
   *
   * @param question question String
   * @param answer   answer String
   */
  public YesNoQuestion(String question, String answer) {
    super(checkArguments(question, answer));
    this.answer = answer.trim();
  }

  /**
   * This function checks if the arguments entered are valid, and if not throws an IllegalArgument
   * exception.
   *
   * @param question the question String.
   * @param answer   the answer String.
   * @return question to be passed to super constructor if no exception is thrown.
   */
  private static String checkArguments(String question, String answer)
          throws IllegalArgumentException {
    if (!answer.trim().equalsIgnoreCase("yes")
            && !answer.trim().equalsIgnoreCase("no")) {
      throw new IllegalArgumentException("Answer for this "
              + "question should be either \"Yes\" or \"No\" ");
    } else if (question.equals("") || question == null) {
      throw new IllegalArgumentException("Question cannot be empty");
    }
    return question;
  }

  /**
   * This function checks the answer inputed against the correct answer to the question.
   */
  protected void checkAnswer() {
    if (this.input.equals("")) {
      this.result = evaluation.INCORRECT;
    } else if (!this.answer.equals("")) {
      if (this.input.equalsIgnoreCase(this.answer)) {
        this.result = evaluation.CORRECT;
      } else {
        this.result = evaluation.INCORRECT;
      }
    } else {
      this.result = evaluation.CORRECT;
    }
  }

  /**
   * CompareTo function comparing a YesNo Question object with a YesNo Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToYesNo(AbstractQuestion other) {
    return other.question.compareTo(this.question);
  }

  /**
   * CompareTo function to compare a YesNo question with a Question object.
   *
   * @param o object of type Question.
   * @return the result of comparison.
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQn = (AbstractQuestion) o;
      return aQn.compareToYesNo(this);
    }
    return 1;
  }
}
