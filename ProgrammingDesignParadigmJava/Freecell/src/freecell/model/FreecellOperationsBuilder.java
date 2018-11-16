package freecell.model;

/**
 * This is an interface to build objects of a Freecell game.
 */
public interface FreecellOperationsBuilder {
  /**
   * Method to alter the number of cascade piles in this instance of the Freecell game.
   *
   * @param c Integer value that denotes the number of cascading piles for this game.
   * @return this FreecellOperationsBuilder Object.
   * @throws IllegalArgumentException when input c is lesser than 4 or greater than 8 as the only
   *                                  allowed values for the number of cascading piles is
   *                                  4,5,6,7,8.
   */
  FreecellOperationsBuilder cascades(int c);

  /**
   * Method to alter the number of open piles in this instance of the Freecell game.
   *
   * @param o Integer value that denotes the number of open piles for this game.
   * @return this FreecellOperationsBuilder Object.
   * @throws IllegalArgumentException when input o is lesser than 1 or greater than 4 as the only
   *                                  allowed values for the number of cascading piles is
   *                                  1,2,3,4.
   */
  FreecellOperationsBuilder opens(int o);

  /**
   * Method to build the Freecell model Object using members of this class.
   *
   * @return new FreecellModel object with the number of cascade piles and the number of open
   *         piles in the game set to the members of this class.
   */
  <K> FreecellOperations<K> build();
}