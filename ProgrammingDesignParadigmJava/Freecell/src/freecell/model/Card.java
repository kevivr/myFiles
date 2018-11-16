package freecell.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a card that can be used in games like Freecell, Poker, Blackjack etc. A
 * card has a value and a suit. Values can be A,2,3,4,5,6,7,8,9,10,J,Q,K and there are only four
 * suits Spade, Club, Diamond, Heart.
 */
public final class Card {
  /**
   * Members of the card Class.
   * <ul>
   * <li>value : The value of this card object.</li>
   * <li>suit: The suit of this card object.</li>
   * <li>values: List of string that depicts acceptable values for the value member of
   * the class</li>
   * </ul>
   */
  private final String value;
  private final String suit;
  private final List<String> values;


  /**
   * Constructor for this card Object. Takes in 2 Strings for value and suit and constructs a card
   * Object using the parameters passed.
   *
   * @param value Value for this card object.
   * @param suit  Suit for this card object.
   * @throws IllegalArgumentException when value or suit passed is invalid.
   */
  public Card(String value, String suit) throws IllegalArgumentException {
    List<String> suits;
    values = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
    suits = Arrays.asList("\u2660", "\u2665", "\u2666", "\u2663");
    Set<String> acceptedSuits = new HashSet<>(suits);
    Set<String> acceptedValues = new HashSet<>(values);

    if (acceptedSuits.contains(suit)) {
      this.suit = suit;
    } else {
      throw new IllegalArgumentException("Invalid card suit, Cannot create card");
    }

    if (acceptedValues.contains(value.toUpperCase())) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Invalid value for card, Cannot create card");
    }
  }

  /**
   * Public Method to return value of this card.
   *
   * @return String that represents value of this card.
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Public method to return the Suit of this card.
   *
   * @return String that represents the suit of this card.
   */
  public String getSuit() {
    return this.suit;
  }

  /**
   * Public method to return String representation of this card.
   *
   * @return String representation of this card object.
   */
  public String toString() {
    return this.value + this.suit;
  }

  /**
   * Public method to check if two card objects are equal. This is determined by checking if both
   * the objects have the same value and the same suit.
   *
   * @return true if both card objects are determined to be equal and false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    return (((Card) o).getSuit().equals(this.suit) && ((Card) o).getValue().equals(this.value));
  }

  /**
   * Public method to calculate hash of this card Object. Hash of two cards that are equal will
   * return the same Integer value when hashCode is called by passing them.
   *
   * @return Integer value that corresponds to the hash value of this card object.
   */
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + (this.suit == null ? 0 : this.suit.hashCode());
    hash = 31 * hash + (this.value == null ? 0 : this.value.hashCode());
    return hash;
  }

  /**
   * Public method to check if a card that is passed as parameter to this function has
   * value exactly one less than the value of this card. If the value of this card is "A",
   * it just returns false as there is no value lesser than "A" when it comes to
   * card object of this class.
   *
   * @param      c the card object on which the check is to be done.
   * @return     true if value of the card passed is exactly one less than the value of
   *             this card and false otherwise.
   */
  public boolean isOneCardBelow(Card c) {
    if (this.value.equals("A")) {
      return false;
    }
    return (c.getValue().equals(this.values.get(this.values.indexOf(this.value) - 1)));
  }

  /**
   * Public method to check if a card that is passed as parameter to this function has value exactly
   * one more than the value of this card. If the value of this card is "K", it just returns false
   * as there is no value bigger than "K" when it comes to card objects of this class.
   *
   * @param      c the card object on which the check is to be done.
   * @return     true if value of the card passed is exactly one less than the value of this card
   *             and false otherwise.
   */
  public boolean isOneCardAbove(Card c) {
    if (this.value.equals("K")) {
      return false;
    }
    return (c.getValue().equals(this.values.get(this.values.indexOf(this.value) + 1)));
  }

  /**
   * Public method to check if a card that is passed as parameter to this function has the same suit
   * as the suit of this card.
   *
   * @param      c the card object on which the check is to be done.
   * @return     true if value of the card passed has same suit as this card's
   *             suit and false otherwise.
   */
  public boolean isCardSameSuit(Card c) {
    return (c.getSuit().equals(this.getSuit()));
  }

  /**
   * Public method to check if a card that is passed as parameter to this function has the same
   * color as the color of this card. Color is black if suit is spade or club and red if suit is
   * diamond or heart.
   *
   * @param      c the card object on which the check is to be done.
   * @return     true if value of the card passed has same color as this card's suit and false
   *             otherwise.
   */
  public boolean isCardSameColor(Card c) {
    if (c.getSuit().equals("\u2660") || c.getSuit().equals("\u2663")) {
      return (this.suit.equals("\u2660") || this.suit.equals("\u2663"));
    } else {
      return (this.suit.equals("\u2665") || this.suit.equals("\u2666"));
    }
  }

}
