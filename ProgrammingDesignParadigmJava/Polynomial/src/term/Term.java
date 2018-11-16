package term;

/**
 * This class represents a term in a polynomial. It has a coefficient and power
 */
public class Term {
  private int coefficient;
  private int power;
  private String term = "";

  /**
   * Construct a term with coefficient and power set to 0.
   */
  public Term() {
    this.coefficient = 0;
    this.power = 0;
  }

  /**
   * Construct a term that has the provided coefficient and power.
   */
  public Term(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Return the coefficient of this term.
   *
   * @return this term's coefficient.
   */
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Return the power of this term.
   *
   * @return this term's power.
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Return new term after adding value to coefficient.
   *
   * @param coefficient the value to add to the current coefficient of the term
   * @return modified term
   *
   */
  public Term add(int coefficient) {
    this.coefficient += coefficient;
    return this;
  }

  /**
   * Evaluate a term when a value for the variable is given.
   *
   * @param number the value to be considered for the variable.
   * @return result of evaluation.
   */
  public Double evaluate(double number) {
    return Math.pow(number, (double) this.power) * this.coefficient;
  }
}
