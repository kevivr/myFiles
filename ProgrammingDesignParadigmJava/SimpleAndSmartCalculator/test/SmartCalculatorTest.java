import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import calculator.Calculator;
import calculator.SmartCalculator;

/**
 * A Junit test class for the SimpleCalculator class that implements Calculator Interface.
 */
public class SmartCalculatorTest {
  private Calculator c1;

  /**
   * Construct sample Object to test Smart Calculator Class.
   */
  @Before
  public void setUp() {
    c1 = new SmartCalculator();
  }

  /**
   * Test single number as input.
   */
  @Test
  public void testString() {
    assertEquals("32", c1.input('3').input('2').getResult());
  }

  /**
   * Test single number and an operator without computation as input.
   */
  @Test
  public void testString2() {
    assertEquals("32+", c1.input('3').input('2').input('+').getResult());
  }

  /**
   * Test an operation succeeded by an uncomputed operator.
   */
  @Test
  public void testString3() {
    assertEquals("45+", c1.input('4').input('2').input('+').input('3').input('+')
            .getResult());
  }

  /**
   * Test an operation succeeded by an equal to operator that gives output of the computation.
   */
  @Test
  public void testTwoOperandsOneOperator() {
    assertEquals("56", c1.input('3').input('2').input('+').input('2').input('4')
            .input('=').getResult());
  }

  /**
   * Test an operation succeeded by multiple equal to operators.
   */
  @Test
  public void testTwoOperandsOneOperatorMultipleEquals() {
    assertEquals("104", c1.input('3').input('2').input('+').input('2').input('4')
            .input('=').input('=').input('=').getResult());
  }

  /**
   * Test passing an operator as the first character.
   */
  @Test
  public void testFirstCharacterOperator() {
    assertEquals("326+46", c1.input('+').input('3').input('2').input('6').input('+')
            .input('4').input('6').getResult());
  }

  /**
   * Test two continuous operators.
   */
  @Test
  public void testContinuousOperators() {
    assertEquals("-33", c1.input('4').input('5').input('+').input('-').input('7')
            .input('8').input('=').getResult());
  }

  /**
   * Test Negative first character.
   */
  @Test
  public void testNegativeFirstOperand() {
    assertEquals("2678", c1.input('-').input('1').input('0').input('3').input('*')
            .input('2').input('6').input('=').getResult());
  }

  /**
   * Test addition followed by multiplication.
   */
  @Test
  public void testConsecutiveOperations() {
    assertEquals("43*67", c1.input('4').input('3').input('+').input('*').input('6')
            .input('7').getResult());
  }

  /**
   * Test zero followed by an operator.
   */
  @Test
  public void testZeroFirstCharacter1() {
    assertEquals("0+6", c1.input('0').input('+').input('8').input('-').input('8')
            .input('+').input('6').getResult());
  }

  /**
   * Test zero followed by a digit.
   */
  @Test
  public void testZeroFirstCharacter2() {
    assertEquals("02+678", c1.input('0').input('2').input('+').input('6').input('7')
            .input('8').getResult());
  }

  /**
   * Test Multiple operations.
   */
  @Test
  public void testMultipleOperands() {
    assertEquals("3275", c1.input('6').input('5').input('7').input('+').input('4')
            .input('-').input('6').input('*').input('5').input('=').getResult());
  }

  /**
   * Test multiple operations without an equal to operator ending the sequence.
   */
  @Test
  public void testMultipleOperands2() {
    assertEquals("655*5", c1.input('6').input('5').input('7').input('+').input('4')
            .input('-').input('6').input('*').input('5').getResult());
  }

  /**
   * Test non valid character as the first character, throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidCharacterBeginning() {
    assertEquals("436+6", c1.input('a').input('4').input('3').input('6')
            .input('+').input('6').getResult());
  }

  /**
   * Test non valid character in between, throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidCharacterInBetween() {
    assertEquals("436+6", c1.input('4').input('3').input('6').input('a').input('+')
            .input('6').getResult());
  }

  /**
   * Test non valid character in the end, throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidCharacterEnd() {
    assertEquals("436+6", c1.input('4').input('3').input('6').input('+').input('6')
            .input('a').getResult());
  }

  /**
   * Test negative result.
   */
  @Test
  public void testNegativeResult1() {
    assertEquals("-21+3", c1.input('4').input('7').input('-').input('6').input('8')
            .input('+').input('3').getResult());
  }

  /**
   * Test operation on a negative result.
   */
  @Test
  public void testNegativeResult2() {
    assertEquals("-38", c1.input('4').input('7').input('-').input('6')
            .input('8').input('+').input('3').input('-').input('2').input('0')
            .input('=').getResult());
  }

  /**
   * Test Clearing result in begining.
   */
  @Test
  public void testClearBegining() {
    assertEquals("-38", c1.input('C').input('4').input('7').input('-').input('6')
            .input('8').input('+').input('3').input('-').input('2').input('0').input('=')
            .getResult());
  }

  /**
   * Test Clearing result in the middle.
   */
  @Test
  public void testClearMiddle() {
    assertEquals("-9", c1.input('4').input('7').input('-').input('6').input('C')
            .input('8').input('+').input('3').input('-').input('2').input('0').input('=')
            .getResult());
  }

  /**
   * Test Clearing result in the end.
   */
  @Test
  public void testClearEnd() {
    assertEquals("", c1.input('4').input('7').input('-').input('6').input('8')
            .input('+').input('3').input('-').input('2').input('0').input('=').input('C')
            .getResult());
  }

  /**
   * Test two consecutive operators inputed.
   */
  @Test
  public void testTwoOperatorsSimultaneous() {
    assertEquals("115+3", c1.input('4').input('7').input('-').input('+').input('6')
            .input('8').input('+').input('3').getResult());
  }

  /**
   * Test negative result.
   */
  @Test
  public void testNegative() {
    assertEquals("-13", c1.input('3').input('-').input('6').input('-').input('1')
            .input('0').input('=').getResult());
  }

  /**
   * Test Result zero.
   */
  @Test
  public void testAfterEquals() {
    assertEquals("0", c1.input('3').input('4').input('7').input('+').input('5').input('6')
            .input('=').input('-').input('4').input('0').input('3').input('=').getResult());
  }

  /**
   * Test big number operand.
   */
  @Test
  public void testOverFlow() {
    for (int i = 0; i < 10; i++) {
      c1.input('1');
    }
    assertEquals("1111111111", c1.getResult());
  }

  /**
   * Test big number second operand.
   */
  @Test
  public void testOverFlow2() {
    c1.input('3').input('4').input('+');
    for (int i = 0; i < 10; i++) {
      c1.input('1');
    }
    assertEquals("34+1111111111", c1.getResult());
  }

  /**
   * Test second operand overflowing gives run time exception.
   */
  @Test(expected = RuntimeException.class)
  public void testOverFlow3() {
    c1.input('3').input('4').input('+');
    for (int i = 0; i < 11; i++) {
      c1.input('1');
    }
    assertEquals("34+11111111111", c1.getResult());
  }

  /**
   * Test first operand overflow gives run time exception.
   */
  @Test(expected = RuntimeException.class)
  public void testOverFlow4() {
    for (int i = 0; i < 11; i++) {
      c1.input('1');
    }
    assertEquals("11111111111", c1.getResult());
  }

  /**
   * Test Result overflow.
   */
  @Test
  public void testResultOverFlow() {
    for (int i = 0; i < 10; i++) {
      c1.input('1');
    }
    c1.input('+');
    for (int i = 0; i < 10; i++) {
      c1.input('1');
    }
    assertEquals("45", c1.input('=').input('+').input('4').input('5').input('=')
            .getResult());
  }

  /**
   * Test simple addition in calculator.
   */
  @Test
  public void SimpleAddition() {
    assertEquals("46", c1.input('2').input('3').input('+').input('2').input('3').input('=')
            .getResult());
  }

  /**
   * Test simple multiplication in calculator.
   */
  @Test
  public void SimpleMultiplication() {
    assertEquals("46", c1.input('2').input('3').input('*').input('2').input('=')
            .getResult());
  }

  /**
   * Test simple subtraction in calculator.
   */
  @Test
  public void SimpleSubtraction() {
    assertEquals("46", c1.input('6').input('9').input('-').input('2').input('3').input('=')
            .getResult());
  }

  /**
   * Handle Invalid Input.
   */
  @Test
  public void testInvalidThirdInput() {
    try {
      assertEquals("7", c1.input('3').input('+').input('4').input('-').input('a'));
    } catch (IllegalArgumentException e) {
      assertEquals("7-1", c1.input('1').getResult());
    }
  }

  /**
   * Handle Overflowing operand.
   */
  @Test
  public void testResultOverflow() {
    try {
      for (int i = 0; i < 11; i++) {
        c1.input('1');
      }
    } catch (RuntimeException e) {
      assertEquals("1111111111", c1.getResult());
    }
  }

  /**
   * Test multiple equal to's after an operation.
   */
  @Test
  public void testMultipleEqualsAfterOperation() {
    c1.input('2').input('3').input('+').input('3').input('-').input('4').input('3')
            .input('=').input('=').input('=');
    assertEquals("-103", c1.getResult());
  }

  /**
   * Test operator after equal to.
   */
  @Test
  public void testOperationAfterEqualTo() {
    c1.input('2').input('3').input('=').input('*').input('=').input('3').input('2').input('=');
    assertEquals("52932", c1.getResult());
  }

  /**
   * Test multiplication overflow.
   */
  @Test
  public void testMultiplicationOverflow() {
    for (int i = 0; i < 10; i++) {
      c1.input('1');
    }
    c1.input('*').input('2').input('=');
    assertEquals("0", c1.getResult());
  }

  /**
   * Handle subtraction overflow.
   */
  @Test
  public void testSubtractionOverflow() {
    c1.input('1').input('-');
    c1.input('2').input('1').input('4').input('7').input('4').input('8').input('3').input('6')
            .input('4').input('6').input('-').input('3').input('=');
    assertEquals("0", c1.getResult());
  }
}
