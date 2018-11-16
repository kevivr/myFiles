package polynomial;

import term.Term;

import java.util.function.Function;
import java.util.function.Predicate;

public final class PolynomialEmptyNode implements PolynomialNode {

  /**
   * Add the given term to the polynomial and return the modified polynomial.
   *
   * @param coefficient coefficient of term to be added to the polynomial.
   * @param power       power of term to be added to the polynomial.
   * @return the head of the resulting polynomial.
   */
  @Override
  public PolynomialNode addTerm(int coefficient, int power) {
    if (coefficient == 0) {
      return new PolynomialEmptyNode();
    }
    return new PolynomialElementNode(new Term(coefficient, power), this);
  }

  /**
   * A method to return the degree of this Polynomial.
   *
   * @return result the degree of the polynomial
   */
  @Override
  public int getDegree() {
    return 0;
  }

  /**
   * A map higher order function on this PolynomialNode, that returns the corresponding
   * PolynomialNode after the function has been performed.
   *
   * @param converter the function that runs on this node and nodes succeeding it.
   * @return the resulting PolynomialNode.
   */
  @Override
  public PolynomialNode map(Function converter) {
    return new PolynomialEmptyNode();
  }

  /**
   * A filter higher order function on this PolynomialNode and nodes succeeding it, that returns the
   * nodes that obey a predicate test.
   *
   * @param tester the test that runs on each PolynomialNode.
   * @return the polynomialNodes obtained after the filter.
   */
  @Override
  public PolynomialNode filter(Predicate tester) {
    return new PolynomialEmptyNode();
  }

  /**
   * A reduce higher ordrer function on this PolynomialNode and nodes succeeding it,
   * that returns a Double after running the acccumulator function on each node.
   *
   * @param identity    the starting value for the accumulator.
   * @param accumulator the function that runs on each polynomialNode.
   * @return the result of running accumulator on each polynomialNode.
   */
  @Override
  public Double reduce(Double identity, Function accumulator) {
    return 0.0;
  }

  /**
   * A function that returns the term that this Polynomial Node represents.
   * @return term of this PolynomialNode.
   */
  @Override
  public Term getNodeTerm() {
    return new Term();
  }

  /**
   * A method to return the String of the polynomial.
   *
   * @return polynomial in String format.
   */
  @Override
  public String toString() {
    return "";
  }
}
