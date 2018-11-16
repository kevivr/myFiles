package polynomial;

import java.util.function.Function;
import java.util.function.Predicate;

import term.Term;

public interface PolynomialNode {
  /**
   * Add the given term to the polynomial and return the modified polynomial.
   *
   * @param coefficient coefficient of term to be added to the polynomial.
   * @param power       power of term to be added to the polynomial.
   * @return the head of the resulting polynomial.
   */
  PolynomialNode addTerm(int coefficient, int power);

  /**
   * A method to return the degree of this Polynomial.
   *
   * @return result the degree of the polynomial
   */
  int getDegree();

  /**
   * A method to return the String of the polynomial.
   * @return polynomial in String format.
   */
  String toString();

  /**
   * A map higher order function on this PolynomialNode, that returns the
   * corresponding PolynomialNode after the function has been performed.
   *
   * @param converter the function that runs on this node and nodes succeeding it.
   * @return the resulting PolynomialNode.
   */
  PolynomialNode map(Function converter);

  /**
   * A filter higher order function on this PolynomialNode and nodes succeeding it,
   * that returns the nodes that obey a predicate test.
   *
   * @param test the test that runs on each PolynomialNode.
   * @return the polynomialNodes obtained after the filter.
   */
  PolynomialNode filter(Predicate test);

  /**
   * A reduce higher ordrer function on this PolynomialNode and nodes succeeding it,
   * that returns a Double after running the acccumulator function on each node.
   *
   * @param identity    the starting value for the accumulator.
   * @param accumulator the function that runs on each polynomialNode.
   * @return the result of running accumulator on each polynomialNode.
   */
  Double reduce(Double identity, Function accumulator);

  /**
   * A function that returns the term that this Polynomial Node represents.
   * @return term of this PolynomialNode.
   */
  Term getNodeTerm();

}
