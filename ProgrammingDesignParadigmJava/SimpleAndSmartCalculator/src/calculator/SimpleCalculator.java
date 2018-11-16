package calculator;

/**
 * This class represents a Simple Calculator which is an implementation of the Calculator interface.
 * Takes in as input a sequence of operands and operators and has a member result that shows either
 * what was entered so far, or the result. Only valid characters that can be part of the input are *
 * operand characters 0-9, valid operators +,-,* and the character C that will be used to clear
 * calculator inputs.
 */
public class SimpleCalculator extends AbstractCalculator {
  /**
   * Constructs a SimpleCalculator object, initialises different members by calling the reset
   * function.
   */
  public SimpleCalculator() {
    reset();
  }

  /**
   * Function to process the first character input of the calculator.
   *
   * @param inp the character input.
   */
  @Override
  protected void processFirstCharacter(char inp) {
    if (!Character.isDigit(inp) && inp != 'C') {
      throw new IllegalArgumentException("First Character has to be a digit in SimpleCalculator");
    } else if (inp != 'C') {
      this.expression = this.expression.concat(Character.toString(inp));
      this.result = this.result.concat(Character.toString(inp));
    }
  }

  /**
   * Function to process the operator entered.
   *
   * @param inp the character input.
   */
  @Override
  protected void processOperator(char inp) {
    if (isAllowedSpecialCharacter(this.result.charAt(this.result.length() - 1))) {
      throw new IllegalArgumentException("Cannot have two operators consecutively");
    } else if (this.operand1 == Integer.MIN_VALUE) {
      this.operand1 = Integer.parseInt(this.expression);
      this.operator = inp;
      this.expression = "";
      this.result = this.result.concat(Character.toString(inp));
      this.previousCharacterEqualToFlag = false;
    } else if (this.operand2 == Integer.MIN_VALUE && this.operand1 != Integer.MIN_VALUE) {
      this.operand2 = Integer.parseInt(this.expression);
      int output = calculateOperation(this.operand1, this.operand2, this.operator);
      this.operand1 = output;
      this.operand2 = Integer.MIN_VALUE;
      this.operator = inp;
      this.expression = "";
      this.result = Integer.toString(output).concat(Character.toString(inp));
      this.previousCharacterEqualToFlag = false;
    }
  }

  /**
   * Function to process the equals to operator entered.
   *
   * @param inp the characted input.
   */
  @Override
  protected void processEqualsCharacter(char inp) {
    if (isAllowedSpecialCharacter(result.charAt(result.length() - 1))) {
      throw new IllegalArgumentException("Cannot have two operators consecutively");
    } else if (this.operand1 != Integer.MIN_VALUE && this.operand2 == Integer.MIN_VALUE
            && this.operator != ' ') {
      this.operand2 = Integer.parseInt(this.expression);
      int output = calculateOperation(this.operand1, this.operand2, this.operator);
      this.operand1 = Integer.MIN_VALUE;
      this.operand2 = Integer.MIN_VALUE;
      this.operator = ' ';
      this.expression = Integer.toString(output);
      this.result = Integer.toString(output);
    }
    this.previousCharacterEqualToFlag = true;
  }
}
