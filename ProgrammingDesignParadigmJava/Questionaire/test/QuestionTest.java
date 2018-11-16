import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

import questions.Question;
import questions.MultipleAnswer;
import questions.MultipleChoice;
import questions.LikertQuestion;
import questions.YesNoQuestion;

/**
 * A Junit test class to test various types of Questions.
 */
public class QuestionTest {
  private Question qn1;
  private Question qn2;
  private Question qn3;
  private Question qn4;
  private Question qn5;
  private Question qn6;
  private Question qn7;
  private Question qn8;

  /**
   * Construct sample Objects to test different Question classes.
   */
  @Before
  public void setUp() {
    qn1 = new YesNoQuestion("Is Ronaldo the best?", "Yes");
    qn2 = new YesNoQuestion("Is Messi the best", "No");
    qn3 = new LikertQuestion("Are you satisfied with your food Sir?");
    qn4 = new MultipleChoice("Who is the best footballer?",
            "Cristiano Messi Neymar Hazard Bale", "1");
    qn5 = new MultipleAnswer("Who are the best footballers?",
            "Ronaldo Messi Hazard Bale Modric Salah", "1 2 3 4 5 6");
    qn6 = new MultipleAnswer("What is the best footballer",
            "Cristiano Messi Hazard Neymar", "1");
    qn7 = new MultipleChoice("Capital of India",
            "Bangalore Delhi Mumbai Chennai Kolkata Hyderabad", "2");
    qn8 = new LikertQuestion("Rate yourself");
  }

  /**
   * Test proper creation of YesNoQuestion with answer Yes.
   */
  @Test
  public void testYesYesNo() {
    Question sampleQn = new YesNoQuestion("Can he do it?", "Yes");
    assertEquals("Can he do it?", sampleQn.getQuestion());
  }

  /**
   * Test proper creation of YesNoQuestion with answer No.
   */
  @Test
  public void testNoYesNo() {
    Question sampleQn = new YesNoQuestion("Can he not do it?", "No");
    assertEquals("Can he not do it?", sampleQn.getQuestion());
  }

  /**
   * Test creation of YesNoQuestion with invalid answer argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAnswerYesNo() {
    Question sampleQn = new YesNoQuestion("Is he capable?", "sure");
  }

  /**
   * Test creation of YesNoQuestion with invalid question argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalQuestionYesNo() {
    Question sampleQn = new YesNoQuestion("", "Yes");
  }

  /**
   * Test correct answer entered Yes.
   */
  @Test
  public void correctAnswerYesNo() {
    assertEquals("Correct", qn1.input("Yes").evaluateAnswer());
  }

  /**
   * Test correct answer entered YES.
   */
  @Test
  public void correctAnswer2YesNo() {
    assertEquals("Correct", qn1.input("YES").evaluateAnswer());
  }

  /**
   * Test correct answer entered yes.
   */
  @Test
  public void correctAnswer3YesNo() {
    assertEquals("Correct", qn1.input("yes").evaluateAnswer());
  }

  /**
   * Test correct answer entered No.
   */
  @Test
  public void correctAnswer4YesNo() {
    assertEquals("Correct", qn2.input("No").evaluateAnswer());
  }

  /**
   * Test correct answer entered NO.
   */
  @Test
  public void correctAnswer5YesNo() {
    assertEquals("Correct", qn2.input("NO").evaluateAnswer());
  }

  /**
   * Test correct answer entered no.
   */
  @Test
  public void correctAnswer6YesNo() {
    assertEquals("Correct", qn2.input("no").evaluateAnswer());
  }

  /**
   * Test incorrect answer entered.
   */
  @Test
  public void incorrectAnswerYesNo() {
    assertEquals("Incorrect", qn1.input("not sure").evaluateAnswer());
    assertEquals("Incorrect", qn2.input("definitely").evaluateAnswer());
  }

  /**
   * Test answer incorrect when yes no substrings found but by itself contains other characters.
   */
  @Test
  public void incorrectAnswer2YesNo() {
    assertEquals("Incorrect", qn1.input("YesYESyes").evaluateAnswer());
    assertEquals("Incorrect", qn2.input("definitely").evaluateAnswer());
  }

  /**
   * Test creation of LikertQuestion object.
   */
  @Test
  public void likertQuestionCreation() {
    Question sampleQn = new LikertQuestion("Rate me");
    String t;
    t = "1.Strongly Agree, 2.Agree, 3.Neither Agree or Disagree, 4.Disagree, 5.Strongly Disagree";
    assertEquals(
            "Rate me\n" + t,sampleQn.getQuestion());
  }

  /**
   * Test failure in creation of LikertQuestion object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void likertCreationFailure() {
    Question sampleQn = new LikertQuestion("");
  }

  /**
   * Test passing acceptable input.
   */
  @Test
  public void correctInputLikert() {
    assertEquals("Correct", qn3.input("1").evaluateAnswer());
  }

  /**
   * Test passing unacceptable input.
   */
  @Test
  public void incorrectInputLikert() {
    assertEquals("Incorrect", qn3.input("8").evaluateAnswer());
  }

  /**
   * Test passing unacceptable input.
   */
  @Test
  public void incorrectInput2Likert() {
    assertEquals("Incorrect", qn3.input("").evaluateAnswer());
  }

  /**
   * Test passing unacceptable input.
   */
  @Test
  public void incorrectInput3Likert() {
    assertEquals("Incorrect", qn3.input("afasdf").evaluateAnswer());
  }

  /**
   * Test trailing and leading spaces.
   */
  @Test
  public void testSpaces() {
    Question sampleQn = new YesNoQuestion("sdasdfasdfasdf", " Yes ");
    assertEquals("Correct", sampleQn.input(" Yes").evaluateAnswer());
  }

  /**
   * Test trailing and leading spaces Likert Question.
   */
  @Test
  public void testSpacesLikert() {
    assertEquals("Correct", qn3.input("2 ").evaluateAnswer());
  }

  /**
   * Test creation of MultipleChoice question object.
   */
  @Test
  public void testMultipleChoiceCreation() {
    Question sampleQn = new MultipleChoice("Which is better?",
            "fast slow slower fastest", "1");
    assertEquals("Which is better?\n" + "fast slow slower fastest", sampleQn.getQuestion());
  }

  /**
   * Test failure in creation of MultipleChoice question object because question was empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail1() {
    Question sampleQn = new MultipleChoice("", "fast slow slower", "1");
  }

  /**
   * Test failure in creation of MultipleChoice question object because more than one correct answer
   * was mentioned.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail2() {
    Question sampleQn = new MultipleChoice("adfasdf", "fast slow slower",
            "1 2");
  }

  /**
   * Test failure in creation of MultipleChoice question object because answer was of wrong format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail3() {
    Question sampleQn = new MultipleChoice("asdfasdfasdfv", "fast slow slower",
            "first");
  }

  /**
   * Test failure in creation of MultipleChoice question object because answer was not according to
   * the number of options.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail4() {
    Question sampleQn = new MultipleChoice("sdafasdfasdf",
            "fast slow slower slowest", "6");
  }

  /**
   * Test failure in creation of MultipleChoice question object because options format was not
   * correct.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail5() {
    Question sampleQn = new MultipleChoice("sdafasdfasdf",
            "fast slow", "6");
  }

  /**
   * Test failure in creation of MultipleChoice question object because options format was not
   * correct.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceFail6() {
    Question sampleQn = new MultipleChoice("sdafasdfasdf",
            "1 2 3 4 5 6 7 8 9", "6");
  }

  /**
   * Test case when correct answer was inputed.
   */
  @Test
  public void testMultipleChoiceCorrect() {
    assertEquals("Correct", qn4.input("1").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleChoiceIncorrect() {
    assertEquals("Incorrect", qn4.input("2").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleChoiceIncorrect1() {
    assertEquals("Incorrect", qn4.input("12").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleChoiceIncorrect2() {
    assertEquals("Incorrect", qn4.input("1 2").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleChoiceIncorrect3() {
    assertEquals("Incorrect", qn4.input("a").evaluateAnswer());
  }

  /**
   * Test case when answer is correct with trailing and leading spaces.
   */
  @Test
  public void testMultipleChoiceCorrect1() {
    assertEquals("Correct", qn4.input(" 1      ").evaluateAnswer());
  }

  /**
   * Test creation of MultipleAnswer question object.
   */
  @Test
  public void testMultipleAnswerCreation() {
    Question sampleQn = new MultipleAnswer("Which is better?",
            "fast slow slower fastest", "1 2 3 4");
    assertEquals("Which is better?\n" + "fast slow slower fastest", sampleQn.getQuestion());
  }

  /**
   * Test failure in creation of MultipleAnswer question object because question was empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleAnswerFail1() {
    Question sampleQn = new MultipleAnswer("", "fast slow slower", "1");
  }

  /**
   * Test failure in creation of MultipleAnswer question object because answer was of wrong format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleAnswerFail3() {
    Question sampleQn = new MultipleAnswer("asdfasdfasdfv", "fast slow slower",
            "first second");
  }

  /**
   * Test failure in creation of MultipleAnswer question object because answer was not according to
   * the number of options.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleAnswerFail4() {
    Question sampleQn = new MultipleAnswer("sdafasdfasdf",
            "fast slow slower slowest", "6 4 3");
  }

  /**
   * Test failure in creation of MultipleAnswer question object because options format was not
   * correct.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleAnswerFail5() {
    Question sampleQn = new MultipleAnswer("sdafasdfasdf",
            "fast slow", "6");
  }

  /**
   * Test failure in creation of MultipleAnswer question object because options format was not
   * correct.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleAnswerFail6() {
    Question sampleQn = new MultipleAnswer("sdafasdfasdf",
            "1 2 3 4 5 6 7 8 9", "6");
  }

  /**
   * Test case when correct answer was inputed.
   */
  @Test
  public void testMultipleAnswerCorrect() {
    assertEquals("Correct", qn5.input("1 2 3 4 5 6").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleAnswerIncorrect() {
    assertEquals("Correct", qn5.input("2 1 4 3 5 6").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleAnswerIncorrect1() {
    assertEquals("Incorrect", qn5.input("12").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleAnswerIncorrect2() {
    assertEquals("Incorrect", qn5.input("1 2").evaluateAnswer());
  }

  /**
   * Test case when answer is incorrect.
   */
  @Test
  public void testMultipleAnswerIncorrect3() {
    assertEquals("Incorrect", qn5.input("adas").evaluateAnswer());
  }

  /**
   * Test case when answer is correct with trailing and leading spaces.
   */
  @Test
  public void testMultipleAnswerCorrect1() {
    assertEquals("Correct", qn5.input(" 1 2 3 4 5 6     ").evaluateAnswer());
  }

  /**
   * Test case when number of options is lesser than the acceptable answers.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWrongMultipleAnswerFormat() {
    Question sampleQn = new MultipleAnswer("aadffasdfasdf",
            "1 2 3 4 5 6", "1 2 3 4 5 6 7 8");
  }

  /**
   * Test cases for Sorting arrays of Questions.
   */
  @Test
  public void testQuestionsSort1() {
    Object[] arr = {qn1, qn2, qn3, qn4, qn5, qn6, qn7, qn8};
    Object[] arr2 = new Question[]{qn8, qn7, qn6, qn5, qn4, qn3, qn2, qn1};
    Object[] sortedArray = new Question[]{qn2, qn1, qn3, qn8, qn7, qn4, qn6, qn5};
    Object[] sortedArray2 = new Question[]{qn2, qn1, qn3, qn8, qn7, qn4, qn6, qn5};
    Object[] reverseSorted = new Question[]{qn5, qn6, qn4, qn7, qn8, qn3, qn1, qn2};

    Arrays.sort(arr);
    assertArrayEquals(sortedArray, arr);
    //Arrays.sort(arr2);
    //assertArrayEquals(sortedArray, arr2);
    //Arrays.sort(sortedArray2);
    //assertArrayEquals(sortedArray, sortedArray2);
    //Arrays.sort(reverseSorted);
    //assertArrayEquals(sortedArray, reverseSorted);
  }
}
