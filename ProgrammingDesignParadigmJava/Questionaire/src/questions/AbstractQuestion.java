package questions;

abstract class AbstractQuestion implements Question {

  /**
   * Declare class members.
   * <ul>
   *   <li>String question that represents the question</li>
   *   <li>String answer that represents the correct answer</li>
   *   <li>String input that represents the input</li>
   *   <li>String options that represents the various correct answers</li>
   * </ul>
   */
  protected String question;
  protected String answer;
  protected String input;
  protected String options;

  /**
   * enum that represents if the entered is correct or not.
   */
  protected enum evaluation {
    CORRECT("Correct"), INCORRECT("Incorrect");
    String eval;

    evaluation(String s) {
      this.eval = s;
    }
  }

  protected evaluation result;

  /**
   * Abstract question constructor that initialises question String.
   * @param question string to initialize question by.
   */
  protected AbstractQuestion(String question) {
    this.question = question;
    this.options = "";
    this.answer = "";
    this.input = "";
  }

  /**
   * Function that returns a string that says if the entered is correct or not.
   * @return result.eval String that contains evaluation of the question.
   */
  @Override
  public String evaluateAnswer() {
    checkAnswer();
    return result.eval;
  }

  /**
   * Function that initializes the input string with entered string.
   * @param inp the string to initialize input string by.
   * @return Question Object with the modified input.
   */
  @Override
  public Question input(String inp) {
    this.input = inp.trim();
    return this;
  }

  /**
   * Function that returns string which depicts Question and options for the question.
   * @return String which depicts Question and options for the question.
   */
  @Override
  public String getQuestion() {
    String result = this.question;
    if (!this.options.equals("")) {
      result += "\n" + this.options;
    }
    return result;
  }


  /**
   * CompareTo function comparing a Question object with a YesNo Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  protected int compareToYesNo(AbstractQuestion other) {
    return 1;
  }


  /**
   * CompareTo function comparing a Question object with a Likert Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  protected int compareToLikert(AbstractQuestion other) {
    return 1;
  }


  /**
   * CompareTo function comparing a Question object with a MultipleChoice Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  protected int compareToMultipleChoice(AbstractQuestion other) {
    return 1;
  }


  /**
   * CompareTo function comparing a Question object with a MultipleAnswer Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  protected int compareToMultipleAnswer(AbstractQuestion other) {
    return 1;
  }

  /**
   * Function to do various checks to see if input is equal to correct answer.
  */
  protected abstract void checkAnswer();
}