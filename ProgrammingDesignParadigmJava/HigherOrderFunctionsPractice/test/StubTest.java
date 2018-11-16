import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


import grades.Gradebook;
import grades.StudentRecord;

import static org.junit.Assert.assertEquals;


public class StubTest {

  private Gradebook records;
  private Gradebook empty;
  private List<Double> weights;
  private List<Double> finalScores;
  private List<String> letterGrades;
  private List<String> firstNames, lastNames;
  private final int NumAssignments = 4;
  private List<String> letters;
  private List<Double> thresholds;

  @Before
  public void setup() {

    letters = Arrays.asList(new String[]{"F", "D-", "D", "D+", "C-", "C", "C+",
            "B-", "B", "B+", "A-", "A"});
    thresholds = Arrays.asList(new Double[]{60.0, 63.0, 66.0, 70.0, 73.0, 76.0,
            80.0, 83.0, 86.0, 90.0, 93.0, 100.0});
    records = new Gradebook(letters, thresholds);
    empty = new Gradebook(letters, thresholds);
    finalScores = new ArrayList<Double>();
    letterGrades = new ArrayList<String>();
    firstNames = new ArrayList<String>();
    lastNames = new ArrayList<String>();
    int i = 0;
    while (i < input.length) {
      String fName = input[i];
      String lName = input[i + 1];
      double[] points = new double[NumAssignments];
      for (int j = 0; j < NumAssignments; j++) {
        points[j] = 100 * Double.parseDouble(input[i + 2 + j]);
      }

      finalScores.add(Double.parseDouble(input[i + 2 + NumAssignments]));
      letterGrades.add(input[i + 2 + NumAssignments + 1]);
      firstNames.add(fName);
      lastNames.add(lName);

      i = i + 4 + NumAssignments;
      records.addStudent(new StudentRecord(fName, lName, points));
    }

    weights = new ArrayList<Double>();
    weights.add(20.0);
    weights.add(30.0);
    weights.add(40.0);
    weights.add(10.0);

  }

  /**
   * Test Final Scores of each student as returned by getFinalSCcores Function.
   */
  @Test
  public void testIndividualGrades() {
    List<Double> finals = records.getFinalScores(weights);
    for (int i = 0; i < finalScores.size(); i++) {
      assertEquals(finalScores.get(i), finals.get(i), 0.001);
    }
  }

  /**
   * Method to test whether each Student's name is returned by the getStudentNames function.
   */
  @Test
  public void testStudentNames() {
    assertEquals(Arrays.asList("Amit Shesh", "Clark Freifeld", "Aniruddha Tapas",
            "Aditya Sathyanarayan", "Ritika Nair", "Rohan Chitnis", "Vivek Raju",
            "Varun Raju", "Sankalp Vatsh", "Girish Siddhartha", "Vindhya Mandekar",
            "Rohan Halegiri", "Amit Shesh", "Clark Ashwin", "Bob Ryan", "Jack Daniels",
            "Brave Soldier", "Guest pass", "Amit Swarna", "Johny Bravo", "Swat Kats",
            "Guest List", "Ashok RMS", "Ashwath Bharadwaj", "Siddart Rangachari",
            "Prashanth Sunny", "SV Raju", "Anuradhu Raju", "Vishnupriya Nair", "Vishnu MK",
            "Swathy Shesh", "Ryan Reynolds", "Neal Young", "Sweson Benson", "Vishnu Chandra",
            "Shiva Chandra"), records.getStudentNames());
  }

  /**
   * Method to test whether countAboveAverage correctly returns the number of students whose final
   * grade is above average.
   */
  @Test
  public void testCountAboveAverage() {
    assertEquals(18, records.countAboveAverage(weights));
  }

  /**
   * Method to test that average score for all students with a given name is computed correctly.
   */
  @Test
  public void testAverageScoreForName() {
    assertEquals(new Double(70.85489508),
            records.averageScoreForName("Amit", weights));
    assertEquals(new Double(92.53679654),
            records.averageScoreForName("Clark", weights));
  }

  /**
   * Method to test whether count of students with a particular grade is returned correctly.
   */
  @Test
  public void testGradeCount() {
    assertEquals(12, records.countLetterGrade("A", weights));
    assertEquals(6, records.countLetterGrade("A-", weights));
    assertEquals(6, records.countLetterGrade("C-", weights));
    assertEquals(6, records.countLetterGrade("D+", weights));
    assertEquals(6, records.countLetterGrade("F", weights));
    assertEquals(0, records.countLetterGrade("H", weights));
  }

  /**
   * Method to test handling of getFinalScores for empty gradebook.
   */
  @Test
  public void testEmptyGradeBookGetFinalScores() {
    List<Double> result = empty.getFinalScores(weights);
    assertEquals(0, result.size());
  }

  /**
   * Method to test handling of getStudentNames for empty gradebook.
   */
  @Test
  public void testEmptyGradeBookGetStudentNames() {
    List<String> result = empty.getStudentNames();
    assertEquals(0, result.size());
  }

  /**
   * Method to test handling of countAboveAverage  for empty gradebook.
   */
  @Test
  public void testEmptyGradebookCountAboveAverage() {
    assertEquals(0, empty.countAboveAverage(weights));
  }

  /**
   * Method to test handling of countLetterGrade  for empty gradebook.
   */
  @Test
  public void testEmptyGradebookCountLetterGrade() {
    assertEquals(0, empty.countLetterGrade("B-", weights));
  }

  /**
   * Method to test handling of averageScoreForName  for empty gradebook.
   */
  @Test
  public void testEmptyGradebookAverageScoreForName() {
    assertEquals(new Double(0.0), empty.averageScoreForName("", weights));
  }

  /**
   * Method to test exception when weights provided is less than the number of assignments.
   */
  @Test(expected = RuntimeException.class)
  public void testWeightsLesserThanAssignments() {
    ArrayList<Double> weights2 = new ArrayList<Double>() {{
      add(10.0);
      add(60.0);
      add(70.0);
    }};
    records.countAboveAverage(weights2);
  }

  /**
   * Method to test handling of countLetterGrade for a invalid Grade.
   */
  @Test
  public void testInvalidGradeCoutnLetterGrade() {
    assertEquals(0, records.countLetterGrade("Z", weights));
  }

  /**
   * Method to test handling of averageScoreForName for a name that is not present.
   */
  @Test
  public void testNameNotPresentAverageScoreForName() {
    assertEquals(new Double(0.0), records.averageScoreForName("ZZasdadsf", weights));
  }

  /**
   * Method to test handling of averageScoreForName for an invalid name.
   */
  @Test
  public void testNameInvalidAverageScoreForName() {
    assertEquals(new Double(0.0), records.averageScoreForName("____", weights));
  }

  // Data from the Excel file, to be used for testing
  String[] input = {"Amit"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Clark"
          , "Freifeld"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Aniruddha"
          , "Tapas"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Aditya"
          , "Sathyanarayan"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Ritika"
          , "Nair"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Rohan"
          , "Chitnis"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Vivek"
          , "Raju"
          , "0.920833"
          , "0.8"
          , "0.65641"
          , "0.218182"
          , "70.8549"
          , "C-"
          , "Varun"
          , "Raju"
          , "1"
          , "0.888889"
          , "0.9"
          , "0.987013"
          , "92.5368"
          , "A-"
          , "Sankalp"
          , "Vatsh"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Girish"
          , "Siddhartha"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Vindhya"
          , "Mandekar"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Rohan"
          , "Halegiri"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Amit"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Clark"
          , "Ashwin"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Bob"
          , "Ryan"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Jack"
          , "Daniels"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Brave"
          , "Soldier"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Guest"
          , "pass"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Amit"
          , "Swarna"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Johny"
          , "Bravo"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Swat"
          , "Kats"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Guest"
          , "List"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Ashok"
          , "RMS"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Ashwath"
          , "Bharadwaj"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Siddart"
          , "Rangachari"
          , "0.920833"
          , "0.8"
          , "0.65641"
          , "0.218182"
          , "70.8549"
          , "C-"
          , "Prashanth"
          , "Sunny"
          , "1"
          , "0.888889"
          , "0.9"
          , "0.987013"
          , "92.5368"
          , "A-"
          , "SV"
          , "Raju"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Anuradhu"
          , "Raju"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Vishnupriya"
          , "Nair"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Vishnu"
          , "MK"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Swathy"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Ryan"
          , "Reynolds"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Neal"
          , "Young"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Sweson"
          , "Benson"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Vishnu"
          , "Chandra"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Shiva"
          , "Chandra"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
  };

}