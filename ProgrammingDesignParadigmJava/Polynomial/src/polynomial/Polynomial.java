package polynomial;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This interface represents a polynomial and operations on it.
 */
public interface Polynomial {
  /**
   * Add a term to the polynomial.
   *
   * @param coefficient the coefficient of the term to be added.
   * @param power       the power of the term to be added.
   */
  void addTerm(int coefficient, int power);

  /**
   * A method that takes a power and returns the coefficient for the term with that power.
   *
   * @param power the power of the term whose coefficient is to be returned.
   * @return result the coefficient of the term with power equal to the argument passed.
   */
  int getCoefficient(int power);

  /**
   * A method to return the degree of this Polynomial.
   *
   * @return result the degree of the polynomial
   */
  int getDegree();

  /**
   * A method that takes a double precision decimal number and returns a double-precision result
   * which is the evaluation of this polynomial using this argument's value.
   *
   * @param number the value to be used in the evaluation of the polynomial.
   * @return result the evaluation of the polynomial with the argument passed.
   */
  Double evaluate(double number);

  /**
   * A method that takes in another Polynomial object and returns the polynomial obtained by adding
   * the two polynomials.
   *
   * @param other the polynomial to be added to this polynomial.
   * @return result the polynomial  obtained after the addition of the two polynomials.
   */
  Polynomial add(Polynomial other);

  /**
   * A method that returns the polynomial obtained by differentiating this polynomial.
   *
   * @return polynomial obtained on differntiation of this polynomial.
   */
  Polynomial derivative();

  /**
   * A map higher order function on this Polynomial, that returns the corresponding Polynomial after
   * the function has been performed.
   *
   * @param converter the function that runs on each term of the polynomial.
   * @return the resulting Polynomial.
   */
  Polynomial map(Function converter);

  /**
   * A filter higher order function on this Polynomial, that returns the corresponding terms that
   * obey a predicate test.
   *
   * @param tester the test that runs on each term of the polynomial.
   * @return the polynomial obtained after the filter.
   */

  Polynomial filter(Predicate tester);

  /**
   * A reduce higher order function on this Polynomial, that returns a Double after running the
   * acccumulator function on each term of this polynomial.
   *
   * @param identity    the starting value for the accumulator.
   * @param accumulator the function that runs on each term of the polynomial.
   * @return the result of running accumulator on each term of the polynomial.
   */
  Double reduce(Double identity, Function accumulator);

  /**
   * A method that returns a String of the polynomial.
   *
   * @return the resulting string of the polynomial.
   */
  String toString();
}
