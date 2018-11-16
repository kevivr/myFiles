package questions;

import java.util.LinkedHashSet;

/**
 * This class represents a MultipleAnswer type Question. These types of questions can have multiple
 * correct answers.
 */
public class MultipleAnswer extends AbstractQuestion {
  private LinkedHashSet<Character> ansHash = new LinkedHashSet<Character>();

  /**
   * Constructor for MultipleAnswer type objects.
   */
  public MultipleAnswer(String question, String options, String answer) {
    super(checkArguments(question, options, answer));
    this.options = options;
    this.answer = answer;
    this.ansHash = removeDuplicates(this.answer);
  }

  /**
   * Function to return a Hashset of unique elements given a string.
   *
   * @param s the string passed
   * @return LinkedHashset that contains unique elements of the String.
   */
  private LinkedHashSet<Character> removeDuplicates(String s) {
    String temp = s.replace(" ", "");
    char[] tempArray = temp.toCharArray();
    LinkedHashSet<Character> tempHash = new LinkedHashSet<Character>();
    for (int i = 0; i < tempArray.length; i++) {
      tempHash.add(tempArray[i]);
    }
    return tempHash;
  }

  /**
   * This function checks if the arguments entered are valid, and if not throws an IllegalArgument
   * exception.
   *
   * @param question the question String.
   * @param options  the String that contains the different answer choices.
   * @param answer   the answer String.
   * @return question to be passed to super constructor if no exception is thrown.
   */
  private static String checkArguments(String question, String options, String answer)
          throws IllegalArgumentException {
    if (!checkPattern(answer)) {
      throw new IllegalArgumentException("Illegal Answer format");
    } else if (question.equals("") || question == null) {
      throw new IllegalArgumentException("Question cannot be empty");
    } else if (!checkOptionsAnswer(options, answer)) {
      throw new IllegalArgumentException("Illegal Options format");
    }
    return question;
  }

  /**
   * Function that checks if the options string is of a valid format and also checks for different
   * scenarios where the answer entered is not valid for the given options.
   *
   * @param options string
   * @param answer  string
   * @return boolean value depending on the passing or failing of checks.
   */
  private static boolean checkOptionsAnswer(String options, String answer) {
    String[] afterSplit = options.split(" ");
    if (afterSplit.length < 3 || afterSplit.length > 8) {
      return false;
    }
    int optionsLength = options.split(" ").length;
    int answerLength = answer.split(" ").length;
    if (optionsLength < answerLength) {
      return false;
    } else {
      for (String s : answer.split(" ")) {
        if (Integer.parseInt(s) > optionsLength) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Function that checks if the given string is of valid format.
   *
   * @param toCheck string passed to the function
   * @return boolean depending on if the check passed or failed.
   */
  private static boolean checkPattern(String toCheck) {
    String pattern = "(([1-8]{1})[\\s]{1}){1,8}";
    String checkSpace = toCheck.concat(" ");
    return (!toCheck.matches(pattern) || !checkSpace.matches(pattern));
  }

  /**
   * Function that checks if one hashset contains all elements in another hashset.
   *
   * @param set1 first hashset.
   * @param set2 second hashset.
   * @return boolean depending on if set1 contains all elements in set2.
   */
  private boolean setEquals(LinkedHashSet<Character> set1, LinkedHashSet<Character> set2) {
    if (set1 == null || set2 == null || set1.size() != set2.size()) {
      return false;
    }
    return set1.containsAll(set2);
  }

  /**
   * Function to check if the input and correct answer match.
   */
  @Override
  protected void checkAnswer() {
    if (this.input.equals("")) {
      this.result = evaluation.INCORRECT;
    } else if (!checkPattern(this.input)) {
      this.result = evaluation.INCORRECT;
    } else if (this.input == this.answer) {
      this.result = evaluation.CORRECT;
    } else {
      LinkedHashSet<Character> inpHash = new LinkedHashSet<Character>();
      inpHash = removeDuplicates(this.input);
      if (!setEquals(inpHash, this.ansHash)) {
        this.result = evaluation.INCORRECT;
      } else {
        this.result = evaluation.CORRECT;
      }
    }
  }

  /**
   * CompareTo function comparing a MultipleAnswer Question object with a YesNo Question object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToYesNo(AbstractQuestion other) {
    return -1;
  }

  /**
   * CompareTo function comparing a MultipleAnswer Question object with a MultipleChoice Question
   * object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToMultipleChoice(AbstractQuestion other) {
    return -1;
  }

  /**
   * CompareTo function comparing a MultipleAnswer Question object with a MultipleAnswer Question
   * object.
   *
   * @param other Question to be compared with
   * @return the result of comparison.
   */
  @Override
  protected int compareToMultipleAnswer(AbstractQuestion other) {
    return other.question.compareTo(this.question);
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
   * CompareTo function to compare a MultipleAnswer question with a Question object.
   *
   * @param o object of type Question.
   * @return the result of comparison.
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQn = (AbstractQuestion) o;
      return aQn.compareToMultipleAnswer(this);
    }
    return 1;
  }
}
