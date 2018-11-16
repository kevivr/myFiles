import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import freecell.model.Card;

/**
 * A Junit test class for the Card class.
 */
public class CardTest {
  private Card aceOfSpades;
  private Card fiveOfHearts;
  private Card sampleCard;
  private Card validCard;

  /**
   * Construct sample Object to test creation of card.
   */
  @Before
  public void setUp() {
    aceOfSpades = new Card("A", "\u2660");
    fiveOfHearts = new Card("5", "\u2663");
  }

  /**
   * Check if getValue returns expected values.
   */
  @Test
  public void testGetValue() {
    assertEquals("A", aceOfSpades.getValue());
    assertEquals("5", fiveOfHearts.getValue());
  }

  /**
   * Check if getSuit returns expected values.
   */
  @Test
  public void testGetSuit() {
    assertEquals("♠", aceOfSpades.getSuit());
    assertEquals("♣", fiveOfHearts.getSuit());
  }

  /**
   * Tests Symmetric Property. Tests Transitive Property. Tests Reflexive Property.
   */
  @Test
  public void testEquals() {
    sampleCard = new Card("5", "\u2663");
    validCard = new Card("5", "\u2663");
    /*
    Symmetric Property test.
     */
    assertTrue(sampleCard.equals(validCard));
    assertTrue(validCard.equals(sampleCard));
    /*
    Transitive Property test.
     */
    assertTrue(validCard.equals(sampleCard));
    assertTrue(sampleCard.equals(fiveOfHearts));
    assertTrue(validCard.equals(fiveOfHearts));

    assertTrue(fiveOfHearts.equals(sampleCard));
    assertFalse(sampleCard.equals(aceOfSpades));
    /*
    Reflexive Property test.
     */
    assertTrue(sampleCard.equals(sampleCard));
    assertFalse(fiveOfHearts.equals(aceOfSpades));

    assertFalse(fiveOfHearts.equals("Hello"));
  }

  /**
   * Checks if the hash code of the cards give the expected result.
   */
  @Test
  public void testHash() {
    sampleCard = new Card("5", "\u2663");
    validCard = new Card("5", "\u2663");

    assertTrue(sampleCard.hashCode() == validCard.hashCode());

    assertTrue(sampleCard.hashCode() == fiveOfHearts.hashCode());

    assertFalse(sampleCard.hashCode() == aceOfSpades.hashCode());

    assertTrue(sampleCard.hashCode() == sampleCard.hashCode());

    Set<Card> dupCheck = new HashSet<>();
    dupCheck.add(sampleCard);
    dupCheck.add(validCard);
  }

  /**
   * Tests if the isCardAbove/isCardBelow is functioning as expected.
   */
  @Test
  public void testIsUpperAndLower() {
    sampleCard = new Card("5", "\u2663");
    validCard = new Card("4", "\u2663");
    Card validCard2 = new Card("6", "\u2663");
    Card validCard3 = new Card("K", "\u2663");
    Card validCard4 = new Card("4", "\u2666");
    Card validCard5 = new Card("4", "\u2665");
    Card validCard6 = new Card("4", "\u2660");
    assertTrue(sampleCard.isOneCardAbove(validCard2));
    assertTrue(sampleCard.isOneCardBelow(validCard4));
    assertTrue(sampleCard.isOneCardBelow(validCard5));
    assertTrue(sampleCard.isOneCardBelow(validCard6));
    assertTrue(sampleCard.isOneCardBelow(validCard));
    assertFalse(aceOfSpades.isOneCardBelow(validCard2));
    assertFalse(validCard3.isOneCardAbove(validCard2));
  }

  /**
   * Tests if the isCardSameColor functions as expected.
   */
  @Test
  public void isCardSameColor() {
    sampleCard = new Card("5", "\u2663");
    validCard = new Card("4", "\u2660");
    Card validCard2 = new Card("6", "\u2666");
    Card validCard3 = new Card("7", "\u2665");
    assertTrue(sampleCard.isCardSameColor(validCard));
    assertFalse(sampleCard.isCardSameColor(validCard2));
    assertFalse(sampleCard.isCardSameColor(validCard3));
    assertTrue(validCard.isCardSameColor(sampleCard));
    assertTrue(validCard2.isCardSameColor(validCard3));
    assertFalse(validCard2.isCardSameColor(sampleCard));
    assertFalse(validCard2.isCardSameColor(validCard));
    assertTrue(validCard3.isCardSameColor(validCard2));
  }

  /**
   * Tests to check if toString method is working as expected.
   */
  @Test
  public void testToString() {
    assertEquals("5♣",fiveOfHearts.toString());
    assertEquals("A♠",aceOfSpades.toString());
  }

  /**
   * Tests to check if isSameSuit is working as expected.
   */
  @Test
  public void testSameSuit() {
    sampleCard = new Card("5", "\u2663");
    validCard = new Card("4", "\u2660");
    Card validCard2 = new Card("6", "\u2666");
    Card validCard3 = new Card("7", "\u2665");
    assertTrue(aceOfSpades.isCardSameSuit(validCard));
    assertTrue(aceOfSpades.isCardSameSuit(aceOfSpades));
    assertTrue(validCard.isCardSameSuit(aceOfSpades));
    assertFalse(aceOfSpades.isCardSameSuit(sampleCard));
    assertFalse(aceOfSpades.isCardSameSuit(validCard2));
    assertFalse(aceOfSpades.isCardSameSuit(validCard3));
  }
}
