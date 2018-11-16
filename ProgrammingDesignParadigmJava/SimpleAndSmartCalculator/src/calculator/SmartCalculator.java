package calculator;

/**
 * This class represents a Smart Calculator which is an implementation of the Calculator interface.
 * Takes in as input a sequence of operands and operators and has a member result that shows either
 * what was entered so far, or the result. Only valid characters that can be part of the input are
 * operand characters 0-9, valid operators +,-,* and the character C that will be used to clear
 * calculator inputs.
 */
public class SmartCalculator extends AbstractCalculator {
  /**
   * Constructs a SmartCalculator object, initialises different members by calling the reset
   * function.
   */
  public SmartCalculator() {
    reset();
  }

  /**
   * Function to process the first character input of the calculator.
   *
   * @param inp the character input.
   */
  @Override
  protected void processFirstCharacter(char inp) {
    if (!isValidCharacter(inp)) {
      throw new IllegalArgumentException("First Character has to be a valid character.");
    } else if (Character.isDigit(inp)) {
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
    if (this.previousCharacterEqualToFlag) {
      if (!this.expression.equals("")) {
        this.operand1 = Integer.parseInt(this.expression);
        this.expression = "";
      }
      this.operator = inp;
      this.operand2 = Integer.MIN_VALUE;
      this.result = this.result.concat(Character.toString(inp));
    } else if (isAllowedSpecialCharacter(this.result.charAt(this.result.length() - 1))) {
      char[] tempArray = this.result.toCharArray();
      tempArray[this.result.length() - 1] = inp;
      this.result = String.valueOf(tempArray);
      this.operator = inp;
    } else if (this.operand1 == Integer.MIN_VALUE) {
      this.operand1 = Integer.parseInt(this.expression);
      this.operator = inp;
      this.expression = "";
      this.result = this.result.concat(Character.toString(inp));
    } else {
      if (this.operand2 == Integer.MIN_VALUE) {
        this.operand2 = Integer.parseInt(this.expression);
      }
      int output = calculateOperation(this.operand1, this.operand2, this.operator);
      this.operand1 = output;
      this.operand2 = Integer.MIN_VALUE;
      this.operator = inp;
      this.expression = "";
      this.result = Integer.toString(output).concat(Character.toString(inp));
    }
    this.previousCharacterEqualToFlag = false;
  }

  /**
   * Function to process the equals to operator entered.
   *
   * @param inp the characted input.
   */
  @Override
  protected void processEqualsCharacter(char inp) {
    if (this.previousCharacterEqualToFlag) {
      if (this.operator != ' ' && this.operand1 != Integer.MIN_VALUE) {
        int output = calculateOperation(this.operand1, this.operand2, this.operator);
        this.operand1 = output;
        this.expression = "";
        this.result = Integer.toString(output);
      }
    } else if (this.operand1 != Integer.MIN_VALUE && this.operand2 == Integer.MIN_VALUE
            && this.operator != ' ') {
      if (this.expression.equals("")) {
        this.operand2 = this.operand1;
      } else {
        this.operand2 = Integer.parseInt(this.expression);
      }
      int output = calculateOperation(this.operand1, this.operand2, this.operator);
      this.operand1 = output;
      this.expression = "";
      this.result = Integer.toString(output);
    }
    this.previousCharacterEqualToFlag = true;
  }
}
