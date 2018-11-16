package calculator;

abstract class AbstractCalculator implements Calculator {

  /**
   * Declare class members.
   * <ul>
   * <li>String result that stores either what was entered till now or the result</li>
   * <li>String expression that is used to create operands</li>
   * <li>Operand1 contains the first operand</li>
   * <li>Operand2 contains the second operand</li>
   * <li>Operator denoted the operation to be performed on the two operands</li>
   * <li>Previous Character Equal Flag denotes if the previous character entered was
   * the = character</li>
   * </ul>
   */

  protected String result;
  protected String expression;
  protected int operand1;
  protected int operand2;
  protected char operator;
  protected boolean previousCharacterEqualToFlag = false;

  /**
   * Function to reset all members of class that includes result, two operands, operator and the
   * expression String.
   */
  protected void reset() {
    this.result = "";
    this.operand1 = Integer.MIN_VALUE;
    this.operand2 = Integer.MIN_VALUE;
    this.operator = ' ';
    this.expression = "";
  }

  /**
   * Function that returns if a Character is either a digit, +, - or C.
   *
   * @param inp the character to perform the check on.
   * @return true or false depending on if entered character is valid.
   */
  protected static boolean isValidCharacter(char inp) {
    return (Character.isDigit(inp) || isAllowedSpecialCharacter(inp) || inp == '=' || inp == 'C');
  }

  /**
   * Function that checks if a character entered is a valid operator - +,- or *.
   *
   * @param inp the character to perform check on.
   * @return true or false depending on if entered character is a valid operator.
   */
  protected static boolean isAllowedSpecialCharacter(char inp) {
    return (inp == '*' || inp == '+' || inp == '-');
  }

  /**
   * Function to calculate result of an operation given two operands and the operator.
   *
   * @param operand1 first operand.
   * @param operand2 second operand.
   * @param operator operator.
   * @return the resultant value of the operation.
   */
  protected static int calculateOperation(int operand1, int operand2, char operator) {
    long output = 0;
    if (operator == '+') {
      output = (long) operand1 + (long) operand2;
    } else if (operator == '-') {
      output = (long) operand1 - (long) operand2;
    } else if (operator == '*') {
      output = (long) operand1 * (long) operand2;
    }
    if (output >= Integer.MAX_VALUE || output <= Integer.MIN_VALUE) {
      output = 0;
    }
    return (int) output;
  }

  /**
   * Function to check if the integer obtained by type casting the expression string to retrieve
   * operands is not overflowing past the valid integer range.
   *
   * @param inp the digit to be added to the expression string against which the check will be
   *            made.
   * @return boolean value depending on if the value overflowed or not.
   */
  protected boolean checkForOverflow(char inp) {
    long temp = Long.parseLong(this.expression.concat(Character.toString(inp)));
    return (temp < Integer.MAX_VALUE);
  }

  /**
   * Function to check if the length of the current number being constructed exceeds 9 after which
   * the number is checked against max value of integer.
   *
   * @param inp the digit to be added to the expression string against which the check will be
   *            made.
   */
  protected void operandRunTimeExceptionCheck(char inp) {
    boolean isOverFlow = true;
    if (this.expression.length() == 9) {
      isOverFlow = checkForOverflow(inp);
    }
    if (!isOverFlow || this.expression.length() == 10) {
      throw new RuntimeException("Value greater than allowed Int Limit");
    }
  }

  /**
   * Check if the character being entered is  eligible as a calculator input.
   *
   * @param inp the character that is appended to the input that is to be processed.
   * @return object of type Calculator with modified behavior.
   * @throws RuntimeException         when values overflow accepted range of integer.
   * @throws IllegalArgumentException when character entered is not an acceptable calculator
   *                                  character.
   */
  @Override
  public Calculator input(char inp) throws RuntimeException {
    if (this.result.length() == 0) {
      processFirstCharacter(inp);
    } else if (inp == 'C') {
      reset();
    } else if (!isValidCharacter(inp)) {
      throw new IllegalArgumentException("Character not allowed");
    } else if (Character.isDigit(inp)) {
      operandRunTimeExceptionCheck(inp);
      this.expression = this.expression.concat(Character.toString(inp));
      this.result = this.result.concat(Character.toString(inp));
      this.previousCharacterEqualToFlag = false;
    } else if (isAllowedSpecialCharacter(inp)) {
      processOperator(inp);
    } else if (inp == '=') {
      processEqualsCharacter(inp);
    }
    return this;
  }

  /**
   * Function to return the result of the calculator.
   *
   * @return result.
   */
  @Override
  public String getResult() {
    return this.result;
  }

  /**
   * Function to process the first character input of the calculator.
   *
   * @param inp the character input.
   */
  protected abstract void processFirstCharacter(char inp);

  /**
   * Function to process the operator entered.
   *
   * @param inp the character input.
   */
  protected abstract void processOperator(char inp);

  /**
   * Function to process the equals to operator entered.
   *
   * @param inp the characted input.
   */
  protected abstract void processEqualsCharacter(char inp);

}
