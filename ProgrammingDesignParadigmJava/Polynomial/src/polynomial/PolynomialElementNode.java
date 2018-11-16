package polynomial;

import java.util.function.Function;
import java.util.function.Predicate;

import term.Term;

/**
 * This class represents an element node in the Polynomial implementation.
 */
public final class PolynomialElementNode implements PolynomialNode {
  private Term term;
  private PolynomialNode rest;

  /**
   * Construcctor to instantiate Objects of this class.
   * @param term the term this Node will represent.
   * @param rest Rest of the nodes in the Polynomial.
   */
  public PolynomialElementNode(Term term, PolynomialNode rest) {
    this.term = term;
    this.rest = rest;
  }

  /**
   * Add the given term to the polynomial and return the modified polynomial.
   *
   * @param coefficient coefficient of term to be added to the polynomial.
   * @param power       power of term to be added to the polynomial.
   * @return the head of the resulting polynomial.
   */
  @Override
  public PolynomialNode addTerm(int coefficient, int power) {
    if (this.term.getPower() < power) {
      return new PolynomialElementNode(new Term(coefficient, power), this);
    } else if (this.term.getPower() == power) {
      this.term.add(coefficient);
      if (this.term.getCoefficient() != 0) {
        return new PolynomialElementNode(this.term, this.rest);
      } else {
        return this.rest;
      }
    } else {
      this.rest = this.rest.addTerm(coefficient, power);
      return this;
    }
  }

  /**
   * A method to return the degree of this Polynomial.
   *
   * @return result the degree of the polynomial
   */
  @Override
  public int getDegree() {
    return this.term.getPower()
            > this.rest.getDegree() ? this.term.getPower() : this.rest.getDegree();
  }

  /**
   * A function that returns the term that this Polynomial Node represents.
   *
   * @return term of this PolynomialNode.
   */
  @Override
  public Term getNodeTerm() {
    return this.term;
  }

  /**
   * A method to return the String of the polynomial.
   *
   * @return polynomial in String format.
   */
  @Override
  public String toString() {
    String result = Integer.toString(this.term.getCoefficient());
    if (this.term.getCoefficient() > 0) {
      result = "+".concat(result);
    }
    if (this.term.getPower() > 0) {
      result = result.concat("x^" + Integer.toString(this.term.getPower()));
    }
    result = result.concat(this.rest.toString());
    return result;
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
    return new PolynomialElementNode((Term) converter.apply(this.term), this.rest.map(converter));
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
    if (tester.test(this.term)) {
      return new PolynomialElementNode(this.term, this.rest.filter(tester));
    }
    return this.rest.filter(tester);
  }

  /**
   * A reduce higher ordrer function on this PolynomialNode and nodes succeeding it, that returns a
   * Double after running the acccumulator function on each node.
   *
   * @param identity    the starting value for the accumulator.
   * @param accumulator the function that runs on each polynomialNode.
   * @return the result of running accumulator on each polynomialNode.
   */
  @Override
  public Double reduce(Double identity, Function accumulator) {
    return identity +
            (double) accumulator.apply(this.term) + this.rest.reduce(identity, accumulator);
  }
}
