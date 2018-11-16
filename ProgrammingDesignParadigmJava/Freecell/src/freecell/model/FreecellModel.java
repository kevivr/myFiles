package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static freecell.model.PileType.CASCADE;
import static freecell.model.PileType.FOUNDATION;
import static freecell.model.PileType.OPEN;
import static java.util.Arrays.asList;


/**
 * This class is an implementation of the FreecellOperations Interface and represents the set of
 * functions usually performed in a Freecell game. It overrides each method provided by the
 * interface that it implements and uses a builder approach to initialize objects of this class. It
 * houses a FreecellBuilder class inside for this purpose.
 */
public class FreecellModel implements FreecellOperations<Card> {

  private final List<Card>[] foundationPiles;
  protected final List<Card>[] openPiles;
  protected final List<Card>[] cascadePiles;
  private final Integer cascadeCount;
  private final Integer openCount;
  protected boolean hasGameStarted = false;

  /**
   * Protected constructor for objects of this class and sub classes that extend this class that
   * initialize count of cascade piles, open piles with the parameters passed to the constructors
   * and the different piles itself as empty lists.
   *
   * @param cascadeCount the number of cascade piles in this game of freecell.
   * @param openCount    the number of open piles in this game of freecell.
   */
  protected FreecellModel(int cascadeCount, int openCount) {
    this.cascadeCount = cascadeCount;
    this.openCount = openCount;

    this.foundationPiles = new List[4];
    for (int i = 0; i < foundationPiles.length; i++) {
      foundationPiles[i] = new LinkedList<>();
    }

    this.openPiles = new List[this.openCount];
    for (int i = 0; i < openPiles.length; i++) {
      openPiles[i] = new LinkedList<>();
    }

    this.cascadePiles = new List[this.cascadeCount];
    for (int i = 0; i < cascadePiles.length; i++) {
      cascadePiles[i] = new LinkedList<>();
    }
  }

  /**
   * Function that creates a FreecellBuilder Object which builds the freecell game object.
   *
   * @return new FreecellBuilder object that further calls functions to build the freecell game
   *         object.
   */
  public static FreecellBuilder getBuilder() {
    return new FreecellBuilder();
  }

  /**
   * This function is used to return a deck that the user can then use to play the game of freecell.
   * It returns a default list of cards that are ordered as follows. Increasing order of cards
   * (A,2,3,4,5,6,7,8,9,10,J,Q,K) in this order of suits (spade, heart, diamond, club).
   *
   * @return List of cards ordered in increasing order of value and suit.
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    List<String> values;
    values = asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
    List<String> suits = asList("\u2660", "\u2665", "\u2666", "\u2663");
    for (String suit : suits) {
      for (String value : values) {
        deck.add(new Card(value, suit));
      }
    }
    return new ArrayList(deck);
  }

  /**
   * Method that starts a new game of Freecell using the deck provided as part  of the function
   * parameters. It shuffles the deck provided if the value of the second boolean parameter is true
   * and doesn't if otherwise. If called when a game is already in progress, it discards the current
   * game and starts a new one.
   *
   * @param deck    the deck to be dealt.
   * @param shuffle if true, shuffle the deck else deal the deck as-is.
   * @throws IllegalArgumentException if the deck contains duplicate cards, invalid cards or if the
   *                                  deck contains lesser than 52 cards.
   */
  @Override
  public void startGame(List<Card> deck, boolean shuffle) throws IllegalArgumentException {
    if (noDuplicatesIn(deck)) {
      if (shuffle) {
        Collections.shuffle(deck);
      }
      for (List<Card> foundationPile : foundationPiles) {
        foundationPile.clear();
      }
      for (List<Card> cascadePile : cascadePiles) {
        cascadePile.clear();
      }
      for (List<Card> openPile : openPiles) {
        openPile.clear();
      }
      int i = 0;
      while (i < 52) {
        int j = 0;
        while (j < this.cascadeCount && i < 52) {
          this.cascadePiles[j].add(deck.get(i));
          j++;
          i++;
        }
      }
      this.hasGameStarted = true;
    }
  }

  /**
   * Method that Checks if there are duplicates in the card or if the number of cards is lesser than
   * 52.
   *
   * @param deck a List of cards to perform the checks on.
   * @return true if no duplicates were found and if the size of the deck is 52.
   * @throws IllegalArgumentException if there is duplicate cards in the deck or  if the deck's size
   *                                  is not 52.
   */
  private boolean noDuplicatesIn(List<Card> deck) {
    Set<Card> dupCheck = new HashSet<>(deck);
    if (dupCheck.size() != 52) {
      throw new IllegalArgumentException("Duplicate cards found in the deck");
    }
    return true;
  }

  /**
   * Move the last card in the source pile to the top of the open pile passed as the destination
   * pile parameter.
   *
   * @param source      pile (List cards) from which to pick.
   * @param destination pile(List cards) to move the card to.
   * @throws IllegalArgumentException if source pile doesn't have a card and if destination pile is
   *                                  not empty.
   */
  private void moveToOpen(List<Card> source, List<Card> destination)
          throws IllegalArgumentException {
    if (source.isEmpty()) {
      throw new IllegalArgumentException("source pile is empty, cannot move from pile");
    }
    if (destination.isEmpty()) {
      Card toMove = source.remove(source.size() - 1);
      destination.add(toMove);
    } else {
      throw new IllegalArgumentException("Open pile should be empty for it to be moved in");
    }
  }

  /**
   * Move the last card in the source pile to the top of the cascade pile passed as the destination
   * pile parameter.
   *
   * @param source      pile (List cards) from which to pick.
   * @param destination pile(List cards) to move the card to.
   * @throws IllegalArgumentException if source pile doesn't have a card or if the card that is on
   *                                  top of the source pile doesn't have value exactly one lesser
   *                                  than the card on top of destination pile or if the card that
   *                                  is on top of the source pile doesn't have different color than
   *                                  the card on top of the destination pile.
   */
  private void moveToCascade(List<Card> source, List<Card> destination)
          throws IllegalArgumentException {
    if (source.isEmpty()) {
      throw new IllegalArgumentException("Source pile is empty, cannot from this pile");
    }
    Card toMove = source.get(source.size() - 1);
    if (destination.isEmpty()) {
      toMove = source.remove(source.size() - 1);
      destination.add(toMove);
    } else {
      Card topOfPileToMoveTo = destination.get(destination.size() - 1);
      if (topOfPileToMoveTo.isOneCardBelow(toMove) && !topOfPileToMoveTo.isCardSameColor(toMove)) {
        toMove = source.remove(source.size() - 1);
        destination.add(toMove);
      } else {
        throw new IllegalArgumentException("Invalid move to Cascade pile");
      }
    }
  }

  /**
   * Move the last card in the source pile to the top of the foundation pile passed as the
   * destination pile parameter.
   *
   * @param source      pile (List cards) from which to pick.
   * @param destination pile(List cards) to move the card to.
   * @throws IllegalArgumentException if source pile doesn't have a card or if the card that is on
   *                                  top of the source pile doesn't have value exactly one greater
   *                                  than the card on top of destination pile or if the card that
   *                                  is on top of the source pile doesn't have the same suit as the
   *                                  card on top of the destination pile.
   */
  private void moveToFoundation(List<Card> source, List<Card> destination)
          throws IllegalArgumentException {
    if (source.isEmpty()) {
      throw new IllegalArgumentException("Source pile is empty, cannot from this pile");
    }
    Card toMove = source.get(source.size() - 1);
    if (destination.isEmpty()) {
      if (toMove.getValue().equals("A")) {
        toMove = source.remove(source.size() - 1);
        destination.add(toMove);
      } else {
        throw new IllegalArgumentException("Attempt to add invalid card");
      }
    } else {
      Card topOfPileToMoveTo = destination.get(destination.size() - 1);
      if (topOfPileToMoveTo.isOneCardAbove(toMove) && topOfPileToMoveTo.isCardSameSuit(toMove)) {
        toMove = source.remove(source.size() - 1);
        destination.add(toMove);
      } else {
        throw new IllegalArgumentException("Invalid move to Foundation pile");
      }
    }
  }

  /**
   * Method to check if the pile type and pile number passed are valid.
   *
   * @param pileToCheck the type of pile.
   * @param pileNumber  the index of the pile of type first parameter.
   * @return the pile that is of type PileType passed as parameter and index the second parameter
   *         passed to the function
   * @throws IllegalArgumentException when index passed is invalid.
   */
  protected List<Card> checkPile(PileType pileToCheck, int pileNumber) {
    if (pileToCheck == CASCADE && pileNumber < this.cascadeCount) {
      return this.cascadePiles[pileNumber];
    }
    if (pileToCheck == OPEN && pileNumber < this.openCount) {
      return this.openPiles[pileNumber];
    }
    if (pileToCheck == FOUNDATION && pileNumber < 4) {
      return this.foundationPiles[pileNumber];
    } else {
      throw new IllegalArgumentException("Invalid pile number");
    }
  }

  /**
   * Move the last card in the source pile to the top of the foundation pile passed as the
   * destination pile parameter.
   *
   * @param source         pile type of the source from which to pick.
   * @param pileNumber     index of the pile of type source from which to pick.
   * @param destination    pile type of the destination from which to pick.
   * @param destPileNumber index of the pile of type destination from which to pick.
   * @throws IllegalStateException    if game hasn't started yet or if the source pile type is
   *                                  foundation pile, or its invalid as checked by the checkPile
   *                                  function.
   * @throws IllegalArgumentException if the move is not possible.
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    if (!this.hasGameStarted || source == FOUNDATION) {
      throw new IllegalStateException("Game hasn't started yet or source is Foundation pile");
    }
    List<Card> sourcePile;
    List<Card> destinationPile;
    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("Invalid pile number or Source pile type");
    } else {
      sourcePile = checkPile(source, pileNumber);
    }
    destinationPile = checkPile(destination, destPileNumber);
    if (destination == CASCADE) {
      moveToCascade(sourcePile, destinationPile);
    } else if (destination == FOUNDATION) {
      moveToFoundation(sourcePile, destinationPile);
    } else if (destination == OPEN) {
      moveToOpen(sourcePile, destinationPile);
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (List<Card> foundationPile : this.foundationPiles) {
      if (foundationPile.size() == 0) {
        return false;
      }
      if (foundationPile.size() != 13 ||
              !foundationPile.get(foundationPile.size() - 1).getValue().equals("K")) {
        return false;
      }
    }
    this.hasGameStarted = false;
    return true;
  }

  /**
   * Return the present state of the game as a string. Return empty String if the game hasn't been
   * started. Otherwise,  the string is formatted as follows:
   * <pre>
   * F1:[b]f11,[b]f12,[b],...,[b]f1n1[n] (Cards in foundation pile 1 in order)
   * F2:[b]f21,[b]f22,[b],...,[b]f2n2[n] (Cards in foundation pile 2 in order)
   * ...
   * Fm:[b]fm1,[b]fm2,[b],...,[b]fmnm[n] (Cards in foundation pile m in
   * order)
   * O1:[b]o11[n] (Cards in open pile 1)
   * O2:[b]o21[n] (Cards in open pile 2)
   * ...
   * Ok:[b]ok1[n] (Cards in open pile k)
   * C1:[b]c11,[b]c12,[b]...,[b]c1p1[n] (Cards in cascade pile 1 in order)
   * C2:[b]c21,[b]c22,[b]...,[b]c2p2[n] (Cards in cascade pile 2 in order)
   * ...
   * Cs:[b]cs1,[b]cs2,[b]...,[b]csps (Cards in cascade pile s in order)
   *
   * where [b] is a single blankspace, [n] is newline. Note that there is no
   * newline on the last line
   * </pre>
   *
   * @return the formatted string as above
   */
  @Override
  public String getGameState() {
    String gameState = "";
    if (!this.hasGameStarted) {
      return "";
    }
    gameState = gameState.concat(getStringPile(FOUNDATION));
    gameState = gameState.concat(getStringPile(OPEN));
    gameState = gameState.concat(getStringPile(CASCADE));
    return gameState.trim();
  }

  /**
   * Helper method to the getState public method. Takes in a pile type as parameter and returns the
   * corresponding state of all piles of pile type passed as a string.
   *
   * @param pileType the pileType whose State is to be returned.
   * @return String that is the state of all piles of the pile type parameter passed.
   */
  private String getStringPile(PileType pileType) {
    String gameState = "";
    List<Card>[] typeOfPile = new List[0];
    String pileString = "";
    switch (pileType) {
      case CASCADE:
        typeOfPile = this.cascadePiles;
        pileString = "C";
        break;
      case FOUNDATION:
        typeOfPile = this.foundationPiles;
        pileString = "F";
        break;
      case OPEN:
        typeOfPile = this.openPiles;
        pileString = "O";
        break;
      default:
        //Because switch is on an enum, this case will never hit.
        break;
    }

    for (int i = 0; i < typeOfPile.length; i++) {
      gameState = gameState.concat(pileString + (i + 1) + ":");
      for (Card c : typeOfPile[i]) {
        gameState = gameState.concat(" " + c.toString() + ",");
      }
      if (typeOfPile[i].size() > 0) {
        gameState = gameState.substring(0, gameState.length() - 1);
      }
      gameState = gameState.concat("\n");
    }
    return gameState;
  }

  /**
   * Class used to build FreecellModel objects. FreecellModel class is built such that the only way
   * an object of FreecellModel can be built is using this builder class. Has methods to alter the
   * number of cascade piles and the number of open piles.
   */
  public static class FreecellBuilder implements FreecellOperationsBuilder {
    /**
     * Members of the class that represents number of cascade piles and number of open piles that
     * the FreecellModel object this builder builds will have.<br>
     * <ul>
     * <li>cascade: int value that represents the number of cascade piles.</li>
     * <li>open: int value that represents the number of open piles.</li>
     * </ul>
     */
    protected int cascade;
    protected int open;

    /**
     * FreecellBuilder protected constructor to set default values for cascade pile count and open
     * pile count.
     */
    protected FreecellBuilder() {
      this.cascade = 8;
      this.open = 4;
    }

    /**
     * Method to alter the number of cascade piles in this instance of the Freecell game.
     *
     * @param c Integer value that denotes the number of cascading piles for this game.
     * @return this FreecellOperationsBuilder Object.
     * @throws IllegalArgumentException when input c is lesser than 4 or greater than 8 as the only
     *                                  allowed values for the number of cascading piles is
     *                                  4,5,6,7,8.
     */
    @Override
    public FreecellOperationsBuilder cascades(int c) throws IllegalArgumentException {
      if (c < 4) {
        throw new IllegalArgumentException("Number of cascading piles for a Freecell game should" +
                "be in greater than 3");
      }
      this.cascade = c;
      return this;
    }

    /**
     * Method to alter the number of open piles in this instance of the Freecell game.
     *
     * @param o Integer value that denotes the number of open piles for this game.
     * @return this FreecellOperationsBuilder Object.
     * @throws IllegalArgumentException when input o is lesser than 1 or greater than 4 as the only
     *                                  allowed values for the number of cascading piles is
     *                                  1,2,3,4.
     */
    @Override
    public FreecellOperationsBuilder opens(int o) throws IllegalArgumentException {
      if (o < 1) {
        throw new IllegalArgumentException("Number of open piles for a Freecell game should " +
                "be greater than 0");
      }
      this.open = o;
      return this;
    }

    /**
     * Method to build the Freecell model Object using members of this class.
     *
     * @return new FreecellModel object with the number of cascade piles and the number of open
     *         piles in the game set to the members of this class.
     */
    @Override
    public FreecellOperations build() {
      return new FreecellModel(this.cascade, this.open);
    }
  }
}
