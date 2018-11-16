import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static junit.framework.TestCase.assertEquals;

public class PolynomialImplTest {
  /**
   * Tester method to test Behavior of Default Constructor.
   */
  @Test
  public void testDefaultConstructor() {
    Polynomial p1 = new PolynomialImpl();
    assertEquals("0", p1.toString());
  }

  /**
   * Tester method to test valid Polymonial creation on passing valid Polynomial String.
   */
  @Test
  public void testStringPolynomialCreationSuccess() {
    String s1 = "4x^3 +3x^1 -5";
    String s2 = "-3x^4 -2x^5 -5 +11x^1";
    String s3 = "102";
    String s4 = "+3x^4 -2x^5 -5 -2x^4 +11x^1";
    String s5 = "0";
    String s6 = "    3x^4   ";
    Polynomial p2 = new PolynomialImpl(s1);
    Polynomial p3 = new PolynomialImpl(s2);
    Polynomial p4 = new PolynomialImpl(s3);
    Polynomial p5 = new PolynomialImpl(s4);
    Polynomial p6 = new PolynomialImpl(s5);
    Polynomial p7 = new PolynomialImpl(s6);
    assertEquals("4x^3+3x^1-5", p2.toString());
    assertEquals("-2x^5-3x^4+11x^1-5", p3.toString());
    assertEquals("102", p4.toString());
    assertEquals("-2x^5+1x^4+11x^1-5", p5.toString());
    assertEquals("0", p6.toString());
    assertEquals("3x^4", p7.toString());
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor1() {
    String s1 = "";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor2() {
    String s1 = "00";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor3() {
    String s1 = "--90";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor4() {
    String s1 = "4y^3 +3y^1 -5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor5() {
    String s1 = "4x^3 +3a^1 -5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor6() {
    String s1 = "4x^3+3x^1-5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor8() {
    String s1 = "4x^33x^1 -5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor7() {
    String s1 = "4x^3++ 3x^1 -5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Tester method to test that exception is thrown when invalid String is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructor9() {
    String s1 = "4x^3+ 3x^1 -5";
    Polynomial p1 = new PolynomialImpl(s1);
  }

  /**
   * Method to test exception is thrown when negative power is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWhenNegativePowerIsProvided() {
    Polynomial p1 = new PolynomialImpl();
    p1.addTerm(10, -6);
  }

  /**
   * Method to test when coefficient is zero.
   */
  @Test
  public void testWhenCoefficientZero() {
    Polynomial p1 = new PolynomialImpl();
    p1.addTerm(0, 11);
    assertEquals("0", p1.toString());
  }

  /**
   * Method to test when coefficient is zero.
   */
  @Test
  public void testWhenCoefficientZero1() {
    Polynomial p1 = new PolynomialImpl();
    p1.addTerm(3, 3);
    p1.addTerm(0, 2);
    assertEquals("3x^3", p1.toString());
  }

  /**
   * Method to test toString method expected Polynomial String.
   */
  @Test
  public void testToStringMethod() {
    Polynomial p = new PolynomialImpl();
    Polynomial p1 = new PolynomialImpl();
    p.addTerm(6, 10);
    p.addTerm(-7, 11);
    p.addTerm(45, 2);
    p.addTerm(-9, 0);
    p1.addTerm(0, 1);
    p1.addTerm(0, 5);
    p1.addTerm(0, 2);
    p1.addTerm(-10, 0);
    assertEquals("-7x^11+6x^10+45x^2-9", p.toString());
    assertEquals("-10", p1.toString());
  }

  /**
   * Method to test evaluation returns expected Output.
   */
  @Test
  public void testEvaluate() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    p.addTerm(-4, 0);
    assertEquals(1.362245045E9, p.evaluate(123));
  }

  /**
   * Method to test evaluation returns expected Output.
   */
  @Test
  public void testEvaluate2() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    p.addTerm(-4, 0);
    assertEquals(1232.0, p.evaluate(4));
  }

  /**
   * Method to test evaluation method returns expected Output.
   */
  @Test
  public void testEvaluate3() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    p.addTerm(-4, 0);
    assertEquals(914336.0, p.evaluate(20));
  }

  /**
   * Method to test evaluation method returns expected Output.
   */
  @Test
  public void testEvaluate4() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    p.addTerm(-4, 0);
    assertEquals(-4.0, p.evaluate(0));
  }

  /**
   * Method to test evaluation method returns expected Output.
   */
  @Test
  public void testEvaluate5() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    assertEquals(0.0, p.evaluate(0));
  }

  /**
   * Method to test evaluation method returns expected Output.
   */
  @Test
  public void testEvaluate6() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-4, 0);
    assertEquals(-4.0, p.evaluate(123));
  }

  /**
   * Method to test evaluation method returns expected Output.
   */
  @Test
  public void testEvaluate7() {
    Polynomial p = new PolynomialImpl();
    assertEquals(0.0, p.evaluate(123));
  }

  /**
   * Method to test evaluation method with negative value passed.
   */
  @Test
  public void testEvaluate8() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    p.addTerm(6, 2);
    p.addTerm(-3, 1);
    assertEquals(2028.0, p.evaluate(-4));
  }

  /**
   * Method to mix polynomial operations.
   */
  @Test
  public void mixOperations() {
    Polynomial p1 = new PolynomialImpl();
    Polynomial p = new PolynomialImpl();
    p.addTerm(-6, 3);
    p.addTerm(6, 4);
    String s = p.toString();
    p1 = p.derivative();
    String s1 = p.toString();
    assertEquals(s, s1);
    p1.addTerm(6, 2);
    p1.addTerm(-3, 1);
    assertEquals("24x^3-12x^2-3x^1", p1.toString());
  }

  /**
   * Method to test if two polynomials are the same.
   */
  @Test
  public void testIfPolynomialsAreSame() {
    Polynomial p1 = new PolynomialImpl();
    Polynomial p2 = new PolynomialImpl();
    Polynomial p3 = new PolynomialImpl();
    Polynomial p4 = new PolynomialImpl();
    Polynomial p5 = new PolynomialImpl();
    Polynomial p6 = new PolynomialImpl();

    p1.addTerm(1, 1);
    p1.addTerm(2, 2);
    p1.addTerm(3, 3);
    p2.addTerm(1, 1);
    p2.addTerm(2, 2);
    p2.addTerm(3, 3);
    assertEquals(true, p3.equals(p4));

    p3.addTerm(-10, 0);
    p4.addTerm(-10, 0);
    assertEquals(true, p3.equals(p4));

    assertEquals(true, p5.equals(p6));

    assertEquals(false, p1.equals(p3));
    assertEquals(false, p2.equals(p4));
    assertEquals(false, p3.equals(p5));
    assertEquals(false, p4.equals(p6));

    assertEquals(true, p4.equals(p4));
    assertEquals(false, p5.equals("asdasd"));
  }

  /**
   * Method to test differentiation of polynomial.
   */
  @Test
  public void testDifferentiationOfPolynomial() {
    Polynomial p = new PolynomialImpl();
    Polynomial p2 = new PolynomialImpl();
    p.addTerm(1, 0);
    p.addTerm(2, 1);
    p.addTerm(3, 2);
    p.addTerm(4, 3);
    p2.addTerm(-1, 0);
    Polynomial p3 = new PolynomialImpl();
    Polynomial p4 = new PolynomialImpl();
    p4.addTerm(2, 1);
    assertEquals("12x^2+6x^1+2", p.derivative().toString());
    assertEquals("0", p2.derivative().toString());
    assertEquals("0", p3.derivative().toString());
    assertEquals("2", p4.derivative().toString());
  }

  /**
   * Method to test double differentiation of polynomial.
   */
  @Test
  public void testDoubleDifferentiation() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(1, 0);
    p.addTerm(2, 1);
    p.addTerm(3, 2);
    p.addTerm(4, 3);
    String s = p.toString();
    Polynomial p1 = p.derivative();
    assertEquals("12x^2+6x^1+2",p1.toString());
    Polynomial p2 = p1.derivative();
    assertEquals("24x^1+6",p2.toString());
    assertEquals(p.toString(),s);

  }

  /**
   * Method to test whether two polynomials are added accordingly.
   */
  @Test
  public void testAddPolynomial() {
    String s1 = "1";
    String s2 = "0";
    String s3 = "+3x^4 -2x^5 -5 -2x^4 +11x^1";
    String s4 = "+3x^4 -2x^5 -5 -2x^4 +11x^1";
    String s5 = "+3x^6 -2x^3 +5 +1x^2";

    Polynomial p1 = new PolynomialImpl(s1);
    Polynomial p2 = new PolynomialImpl(s2);
    Polynomial p3 = new PolynomialImpl(s3);
    Polynomial p4 = new PolynomialImpl(s4);
    Polynomial p5 = new PolynomialImpl(s5);

    String re1 = p4.toString();
    String re2 = p5.toString();

    assertEquals("1", p1.add(p2).toString());
    assertEquals("-2x^5+1x^4+11x^1-5", p2.add(p3).toString());
    assertEquals("-4x^5+2x^4+22x^1-10", p3.add(p4).toString());
    assertEquals("3x^6-2x^5+1x^4-2x^3+1x^2+11x^1", p4.add(p5).toString());
    assertEquals("3x^6-2x^3+1x^2+6", p5.add(p1).toString());
    assertEquals("3x^6-2x^5+1x^4-2x^3+1x^2+11x^1", p4.add(p5).toString());
    assertEquals("-2x^5+1x^4+11x^1-5", p3.add(p2).toString());
    assertEquals("-2x^5+1x^4+11x^1-5", p2.add(p4).toString());
    assertEquals("-2x^5+1x^4+11x^1-4", p1.add(p3).toString());

    assertEquals("3x^6-2x^3+1x^2+5", re2);
    assertEquals("-2x^5+1x^4+11x^1-5", re1);

    assertEquals(true, p1.add(p2).toString().equals(p2.add(p1).toString()));

    assertEquals("3x^6-4x^5+2x^4-2x^3+1x^2+22x^1-4",
            p1.add(p2).add(p3).add(p4).add(p5).toString());
  }

  /**
   * test GetDegree method.
   */
  @Test
  public void testGetDegree() {
    Polynomial p1 = new PolynomialImpl();
    Polynomial p2 = new PolynomialImpl("3x^2 +2x^1 -4x^3 +5x^4");
    Polynomial p3 = new PolynomialImpl("1");
    Polynomial p4 = new PolynomialImpl("0");
    Polynomial p5 = new PolynomialImpl("2x^1");

    assertEquals(0, p1.getDegree());
    assertEquals(4, p2.getDegree());
    assertEquals(0, p3.getDegree());
    assertEquals(0, p4.getDegree());
    assertEquals(1, p5.getDegree());
  }

  /**
   * test GetCoefficient method by passing different values for power.
   */
  @Test
  public void testGetCoefficient() {
    Polynomial p1 = new PolynomialImpl();
    Polynomial p2 = new PolynomialImpl("3x^2 +2x^1 -4x^3 +5x^4");
    Polynomial p3 = new PolynomialImpl("1");
    Polynomial p4 = new PolynomialImpl("0");

    assertEquals(0, p1.getCoefficient(1));
    assertEquals(5, p2.getCoefficient(4));
    assertEquals(-4, p2.getCoefficient(3));
    assertEquals(2, p2.getCoefficient(1));
    assertEquals(3, p2.getCoefficient(2));

    assertEquals(0, p2.getCoefficient(5));
    assertEquals(0, p2.getCoefficient(6));
    assertEquals(0, p2.getCoefficient(7));
    assertEquals(0, p2.getCoefficient(8));

    assertEquals(0, p4.getCoefficient(1));
    assertEquals(1, p3.getCoefficient(0));
  }

  /**
   * Method to test adding Terms into a Polynomial.
   */
  @Test
  public void testAddTerms() {
    Polynomial p1 = new PolynomialImpl();
    p1.addTerm(0, 3);
    assertEquals("0", p1.toString());

    Polynomial p2 = new PolynomialImpl("3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals("-2x^5+1x^4+11x^1-5", p2.toString());
    p2.addTerm(4, 5);
    p2.addTerm(-1, 4);
    p2.addTerm(3, 3);
    p2.addTerm(4, 0);
    p2.addTerm(2, 5);
    assertEquals("4x^5+3x^3+11x^1-1", p2.toString());
  }

}
