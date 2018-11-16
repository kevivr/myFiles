package freecell.model;

import java.util.List;

/**
 * This class is an implementation of the FreecellOperations Interface and represents the set of
 * functions usually performed in a Freecell game. It overrides each method provided by the
 * interface that it implements and uses a builder approach to initialize objects of this class. It
 * houses a FreecellBuilder class inside for this purpose. This is also an implementation of the
 * multiple move allowing Freecell game, where multiple moves can be performed at an instance from
 * one cascade pile to another.
 */
public class FreecellMultiMoveModel extends FreecellModel {

  /**
   * Private constructor for objects of this class that initialize count of cascade piles, open
   * piles with the parameters passed to the constructors and the different piles itself as empty
   * lists.
   *
   * @param cascadeCount the number of cascade piles in this game of freecell.
   * @param openCount    the number of open piles in this game of freecell.
   */
  private FreecellMultiMoveModel(int cascadeCount, int openCount) {
    super(cascadeCount, openCount);
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
    if (source != PileType.CASCADE || destination != PileType.CASCADE) {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    } else {
      if (!this.hasGameStarted) {
        throw new IllegalStateException("Game hasn't started yet or source is Foundation pile");
      }
      List<Card> sourcePile;
      List<Card> destinationPile;
      if (pileNumber < 0 || destPileNumber < 0 || cardIndex < 0) {
        throw new IllegalArgumentException("Invalid pile number or Source pile type");
      } else {
        sourcePile = checkPile(source, pileNumber);
        destinationPile = checkPile(destination, destPileNumber);
      }
      moveCascadeToCascade(sourcePile, cardIndex, destinationPile);
    }
  }

  /**
   * Method to return count of empty piles of a type when passed with the type of pile.
   *
   * @param pileType the type of pile : Cascade, Foundation or Open.
   * @return Integer that is the count of the number of empty piles of the type passed.
   */
  private int countEmptyPiles(List<Card>[] pileType) {
    int count = 0;
    for (List<Card> pile : pileType) {
      if (pile.isEmpty()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Move cards from the given index onwards from the source cascade pile to the destination cascade
   * pile.
   *
   * @param source      pile (List cards) from which to pick.
   * @param destination pile(List cards) to move the card to.
   * @throws IllegalArgumentException if source pile doesn't have a card or if the card at the index
   *                                  passed is not of a different color and value greater than the
   *                                  card being passed or if each successive card at the source
   *                                  pile from the index onwards isn't of a different color and
   *                                  value exactly one lesser or if there aren't enough empty open
   *                                  or cascade piles to perform the multiple move.
   */
  private void moveCascadeToCascade(List<Card> source, int cardIndex, List<Card> destination)
          throws IllegalArgumentException {

    if (source.size() <= cardIndex) {
      throw new IllegalArgumentException("Source pile doesn't have enough " +
              "cards to perform the move");
    }

    int countEmptyOpenPiles = countEmptyPiles(openPiles);
    int countEmptyCascadePiles = countEmptyPiles(cascadePiles);
    if (source.size() - cardIndex > (countEmptyOpenPiles + 1)
            * (int) Math.pow(2, countEmptyCascadePiles)) {
      throw new IllegalArgumentException("Not enough empty piles to move these many cards");
    }
    Card toMove = destination.isEmpty() ?
            new Card("A", "\u2660") : destination.get(destination.size() - 1);
    if (destination.isEmpty() || (source.get(cardIndex).isOneCardAbove(toMove)
            && !source.get(cardIndex).isCardSameColor(toMove))) {
      checkAndMove(cardIndex, source, destination);
    } else {
      throw new IllegalArgumentException("Not forming a valid build");
    }
  }

  /**
   * Method to check if each successive card at the source pile from the index onwards is of a
   * different color and value exactly one lesser.
   *
   * @param cardIndex   the index of the card at the source pile to check from.
   * @param source      the source pile.
   * @param destination the destination pile.
   */
  private void checkAndMove(int cardIndex, List<Card> source, List<Card> destination) {
    int i = cardIndex + 1;
    while (i < source.size()) {
      Card toCheck = source.get(i - 1);
      if (source.get(i).isOneCardAbove(toCheck) && !source.get(i).isCardSameColor(toCheck)) {
        i = i + 1;
      } else {
        throw new IllegalArgumentException("Not forming a valid build");
      }
    }
    List<Card> cardsToMove = source.subList(cardIndex, source.size());
    destination.addAll(cardsToMove);
    source.subList(cardIndex, source.size()).clear();
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
   * Class used to build FreecellMultiMoveModel objects. FreecellMultiMoveModel class is built such
   * that the only way an object of FreecellMultiMoveModel can be built is using this builder class.
   * Has methods to alter the number of cascade piles and the number of open piles.
   */
  public static class FreecellBuilder extends FreecellModel.FreecellBuilder {
    /**
     * FreecellBuilder private constructor to set default values for cascade pile count and open day
     * count.
     */
    private FreecellBuilder() {
      super();
    }

    /**
     * Method to build the FreecellMultiMoveModel Object using members of this class.
     *
     * @return new FreecellMultiMoveModel object with the number of cascade piles and the number of
     *         open piles in the game set to the members of this class.
     */
    @Override
    public FreecellMultiMoveModel build() {
      return new FreecellMultiMoveModel(this.cascade, this.open);
    }
  }
}
