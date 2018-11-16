package polynomial;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import term.Term;

/**
 * This class implements the Polynomial  Interface. It contains a head node that tracks the first
 * term in a polynomial and implements various functions that can be run on a Polynomial.
 */
public class PolynomialImpl implements Polynomial {

  private PolynomialNode head;

  /**
   * A Default constructor for objects of type PolynomialImpl. Returns an empty polynomial.
   */
  public PolynomialImpl() {
    head = new PolynomialEmptyNode();
  }

  /**
   * A parameterized constructor that takes in a String representation of a Polynomial and
   * initializes a PolynomialImpl object using it. It first checks if the String passed is a valid
   * representation of a Polynomial and then passes term by term onto another function that
   * constructs the PolynomialImpl Object.
   * @param s the String Representation of the polynomial that's passed.
   */
  public PolynomialImpl(String s) {
    s = s.trim();
    if (s.equals("")) {
      throw new IllegalArgumentException("Invalid String, Cannot create Polynomial");
    }
    if (s.charAt(0) != '+' && s.charAt(0) != '-') {
      s = "+".concat(s);
    }
    if (s.charAt(s.length() - 1) != ' ') {
      s = s.concat(" ");
    }
    String regexPattern =
            "(([+-]{1})([1-9]\\d*|0{1})([x]{1})(\\^{1})([1-9]\\d*|0{1})([ ])|" +
                    "([+-]{1})([1-9]\\d*|0{1})([ ]))+";
    if (!s.matches(regexPattern)) {
      throw new IllegalArgumentException("Illegal String, Cannot create Polynomial");
    }
    head = new PolynomialEmptyNode();
    String[] arrayOfString = s.split(" ");
    for (String iter : arrayOfString) {
      constructTerm(iter);
    }
  }

  /**
   * A function that takes in a String representation of a Polynomial term, checks if it is a valid
   * representation and then adds that term to the polynomial being constructed.
   * @param s the String representation of the term that is passed.
   */
  private void constructTerm(String s) {
    int coefficient;
    int power;
    String regexPattern =
            "([+-]?)([1-9]\\d*|0{1})([x]{1})(\\^{1})([1-9]\\d*|0{1})|([+-]?)([1-9]\\d*|0{1})";
    Pattern p = Pattern.compile(regexPattern);
    Matcher m = p.matcher(s);
    if (!m.matches()) {
      throw new IllegalArgumentException("Illegal term, cannot be created");
    }
    if (m.group(1) != null) {
      coefficient = Integer.parseInt(m.group(1).concat(m.group(2)));
      power = Integer.parseInt(m.group(5));
      addTerm(coefficient, power);
    } else if (m.group(6) != null) {
      coefficient = Integer.parseInt(m.group(6).concat(m.group(7)));
      power = 0;
      addTerm(coefficient, power);
    }
  }

  /**
   * A private constructor that takes in an object of type PolynomialNode and initializes this
   * PolynomialImpl object by assigning this object's head to the node passed.
   * @param p the PolynomialNode passed.
   */
  private PolynomialImpl(PolynomialNode p) {
    this.head = p;
  }

  /**
   * Add a term to the polynomial.
   *
   * @param coefficient the coefficient of the term to be added.
   * @param power       the power of the term to be added.
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power of the term cannot be zero");
    }
    if (coefficient != 0) {
      head = head.addTerm(coefficient, power);
    }
  }

  /**
   * A method that takes a power and returns the coefficient for the term with that power.
   *
   * @param power the power of the term whose coefficient is to be returned.
   * @return result the coefficient of the term with power equal to the argument passed.
   */
  @Override
  public int getCoefficient(int power) {
    PolynomialNode x = this.head.filter(t -> {
      Term term = (Term) t;
      return term.getPower() == power;
    });
    return x.getNodeTerm().getCoefficient();
  }

  /**
   * A method that takes a double precision decimal number and returns a double-precision result
   * which is the evaluation of this polynomial using this argument's value.
   *
   * @param number the value to be used in the evaluation of the polynomial.
   * @return result the evaluation of the polynomial with the argument passed.
   */
  @Override
  public Double evaluate(double number) {
    double result = this.reduce(0.0, t -> {
      Term term = (Term) t;
      return term.evaluate(number);
    });
    return result;
  }

  /**
   * A method that returns the maximum degree of the polynomial.
   *
   * @return the maximum degree of the polynomial.
   */
  @Override
  public int getDegree() {
    return head.getDegree();
  }

  /**
   * A method that takes in another Polynomial object and returns the polynomial obtained by adding
   * the two polynomials.
   *
   * @param other the polynomial to be added to this polynomial.
   * @return result the polynomial  obtained after the addition of the two polynomials.
   */
  @Override
  public Polynomial add(Polynomial other) {
    int max = Integer.max(this.getDegree(), other.getDegree());
    Polynomial result = new PolynomialImpl();
    while (max >= 0) {
      result.addTerm(this.getCoefficient(max), max);
      result.addTerm(other.getCoefficient(max), max);
      max--;
    }
    return result;
  }

  /**
   * A method that returns a String representation of this Polynomial.
   * @return String representation of Polynomial.
   */
  @Override
  public String toString() {
    String result = head.toString();
    if (result.equals("")) {
      return "0";
    }
    if (result.charAt(0) == '+') {
      result = result.substring(1);
    }
    return result;
  }

  /**
   * A method that checks if an Object passed is equal to this Polynomial.
   * @param o the Object that is checked against.
   * @return boolean true if the Object is equal to this Polynomial and false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof PolynomialImpl)) {
      return false;
    } else {
      return this.toString().equals(o.toString());
    }
  }

  /**
   * Overriding hasCode method to make sure two equal Polynomials return the same hash code.
   * @return int that represents the hashcode for the Polynomial.
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  /**
   * A method that returns the polynomial obtained by differentiating this polynomial.
   *
   * @return polynomial obtained on differentiation of this polynomial.
   */
  @Override
  public Polynomial derivative() {
    Polynomial x = this.filter(t -> {
      Term term = (Term) t;
      return term.getPower() != 0;
    }).map(t -> {
      Term term = (Term) t;
      return new Term(term.getCoefficient() * term.getPower(), term.getPower() - 1);
    });
    return x;
  }

  /**
   * A map higher order function on this Polynomial, that returns the corresponding Polynomial after
   * the function has been performed.
   *
   * @param converter the function that runs on each term of the polynomial.
   * @return the resulting Polynomial.
   */
  @Override
  public Polynomial map(Function converter) {
    return new PolynomialImpl(head.map(converter));
  }

  /**
   * A filter higher order function on this Polynomial, that returns the corresponding terms that
   * obey a predicate test.
   *
   * @param tester the test that runs on each term of the polynomial.
   * @return the polynomial obtained after the filter.
   */
  @Override
  public Polynomial filter(Predicate tester) {
    return new PolynomialImpl(head.filter(tester));
  }

  /**
   * A reduce higher ordrer function on this Polynomial, that returns a Double after running the
   * acccumulator function on each term of this polynomial.
   *
   * @param identity    the starting value for the accumulator.
   * @param accumulator the function that runs on each term of the polynomial.
   * @return the result of running accumulator on each term of the polynomial.
   */
  @Override
  public Double reduce(Double identity, Function accumulator) {
    return head.reduce(identity, accumulator);
  }
}
