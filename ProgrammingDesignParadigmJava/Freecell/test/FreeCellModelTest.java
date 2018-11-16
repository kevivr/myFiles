import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A Junit test class for the FreecellModel class that implements FreecellOperations Interface.
 */
public class FreeCellModelTest {
  private FreecellOperations<Card> firstGame;
  private FreecellOperations<Card> secondGame;
  private List<Card> sampleDeck = new ArrayList<>();

  /**
   * Construct sample deck to be used in freecell game objects in tests.
   */
  @Before
  public void setUp() {
    List<String> values = Arrays.asList("K", "Q", "J", "10", "9", "8",
            "7", "6", "5", "4", "3", "2", "A");
    List<String> suits = Arrays.asList("\u2660", "\u2665", "\u2666", "\u2663");
    for (String s : values) {
      for (String t : suits) {
        sampleDeck.add(new Card(s, t));
      }
    }
  }

  /**
   * Check working of Builder for FreecellOperations class.
   */
  @Test
  public void testFreecellBuilder() {
    firstGame = FreecellModel.getBuilder().build();
    secondGame = FreecellModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    firstGame.startGame(x, false);
    secondGame.startGame(x, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n" +
            "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n" +
            "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n" +
            "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♦, K♦, 8♣\n" +
            "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", firstGame.getGameState());
    assertEquals(firstGame.getGameState(), secondGame.getGameState());
    FreecellOperations thirdGame = FreecellModel.getBuilder().cascades(4).opens(3).build();
    thirdGame.startGame(x, false);
    assertNotEquals(firstGame.getGameState(), thirdGame.getGameState());
    FreecellOperations fourthGame = FreecellModel.getBuilder().build();
    fourthGame.startGame(x, true);
    assertNotEquals(firstGame.getGameState(), fourthGame.getGameState());
  }

  /**
   * Checking number of lines for different values of cascade and open pile values.
   */
  @Test
  public void testFreeCellLineLengths() {
    firstGame = FreecellModel.getBuilder().build();
    secondGame = FreecellModel.getBuilder().build();
    FreecellOperations thirdGame = FreecellModel.getBuilder().cascades(4).opens(3).build();
    List<Card> x = firstGame.getDeck();
    String firstGameState = firstGame.getGameState();
    String secondGameState = secondGame.getGameState();
    String thirdGameState = thirdGame.getGameState();
    assertEquals(firstGameState, secondGameState);
    assertEquals(firstGameState, thirdGameState);
    firstGame.startGame(x, false);
    secondGame.startGame(x, false);
    thirdGame.startGame(x, false);
    firstGameState = firstGame.getGameState();
    secondGameState = secondGame.getGameState();
    thirdGameState = thirdGame.getGameState();

    int firstGameLength = firstGameState.split("\r\n|\r|\n").length;
    int secondGameLength = secondGameState.split("\r\n|\r|\n").length;
    int thirdGameLength = thirdGameState.split("\r\n|\r|\n").length;

    assertEquals(16, firstGameLength);
    assertEquals(16, secondGameLength);
    assertEquals(11, thirdGameLength);
    int open = 4;
    int cascade = 7;
    FreecellOperations fourthGame = FreecellModel.getBuilder()
            .cascades(cascade).opens(open).build();
    fourthGame.startGame(x, true);
    assertEquals(cascade + open + 4,
            fourthGame.getGameState().split("\r\n|\r|\n").length);
  }

  /**
   * Check if Object for more than 9 cascade piles is created.
   */
  @Test
  public void testFreecellBuilderWokrsMoreThanEightCascades() {
    firstGame = FreecellModel.getBuilder().cascades(9).opens(4).build();
    secondGame = FreecellModel.getBuilder().cascades(9).opens(4).build();
    assertEquals("", firstGame.getGameState());
    firstGame.startGame(sampleDeck, false);
    secondGame.startGame(sampleDeck, true);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♥, 9♦, 7♣, 4♠, 2♥\n" +
            "C2: K♥, J♦, 9♣, 6♠, 4♥, 2♦\n" +
            "C3: K♦, J♣, 8♠, 6♥, 4♦, 2♣\n" +
            "C4: K♣, 10♠, 8♥, 6♦, 4♣, A♠\n" +
            "C5: Q♠, 10♥, 8♦, 6♣, 3♠, A♥\n" +
            "C6: Q♥, 10♦, 8♣, 5♠, 3♥, A♦\n" +
            "C7: Q♦, 10♣, 7♠, 5♥, 3♦, A♣\n" +
            "C8: Q♣, 9♠, 7♥, 5♦, 3♣\n" +
            "C9: J♠, 9♥, 7♦, 5♣, 2♠", firstGame.getGameState());
  }

  /**
   * Check if Object for more than 9 cascade piles is created.
   */
  @Test
  public void testFreecellBuilderWokrsMoreThanFourOpens() {
    firstGame = FreecellModel.getBuilder().cascades(7).opens(6).build();
    assertEquals("", firstGame.getGameState());
    firstGame.startGame(sampleDeck, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "C1: K♠, Q♣, 10♦, 8♥, 6♠, 5♣, 3♦, A♥\n" +
            "C2: K♥, J♠, 10♣, 8♦, 6♥, 4♠, 3♣, A♦\n" +
            "C3: K♦, J♥, 9♠, 8♣, 6♦, 4♥, 2♠, A♣\n" +
            "C4: K♣, J♦, 9♥, 7♠, 6♣, 4♦, 2♥\n" +
            "C5: Q♠, J♣, 9♦, 7♥, 5♠, 4♣, 2♦\n" +
            "C6: Q♥, 10♠, 9♣, 7♦, 5♥, 3♠, 2♣\n" +
            "C7: Q♦, 10♥, 8♠, 7♣, 5♦, 3♥, A♠", firstGame.getGameState());
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail2() {
    firstGame = FreecellModel.getBuilder().cascades(3).opens(4).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail3() {
    firstGame = FreecellModel.getBuilder().cascades(8).opens(0).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail4() {
    firstGame = FreecellModel.getBuilder().cascades(3).opens(5).build();
  }

  /**
   * Check if Object is created when legal cascade and open values passed.
   */
  @Test
  public void testFreecellBuilder1() {
    firstGame = FreecellModel.getBuilder().cascades(4).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellModel.getBuilder().cascades(7).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellModel.getBuilder().cascades(8).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellModel.getBuilder().cascades(4).opens(1).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellModel.getBuilder().cascades(4).opens(4).build();
    assertEquals("", firstGame.getGameState());
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalidss() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid2() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalids() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid3() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("11", "\u2660"));
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid4() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("10", "\u2662"));
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOver() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOverSh() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when game hasn't begun.
   */
  @Test
  public void testIsGameOverX() {
    firstGame = FreecellModel.getBuilder().build();
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1Shu() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2Shu() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for true isGameOver.
   */
  @Test
  public void testIsGameOverTrue() {

    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    int j = 0;
    for (int i = 0; i < 4; i++) {
      firstGame.move(PileType.CASCADE, i, 12, PileType.FOUNDATION, i);
      if (i == 3) {
        j++;
        if (j < 13) {
          i = -1;
        }
      }
    }
    assertTrue(firstGame.isGameOver());
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Tests successful move to Open pile.
   */
  @Test
  public void testMoveOpenPile() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 2, 10, PileType.OPEN, 3);
    firstGame.move(PileType.CASCADE, 2, 10, PileType.OPEN, 2);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: 3♦\n" +
            "O4: A♦\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣",firstGame.getGameState());
  }

  /**
   * Tests move failure from Cascade pile to Open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadePile2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.OPEN, 7);
  }

  /**
   * Test to check if exception is thrown when attempting to make a move before game has begun.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveInvalid() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
  }

  /**
   * Catching exception and testing game state when passed invalid deck to start method.
   */
  @Test
  public void testInvalidDeckException() {
    try {
      firstGame = FreecellModel.getBuilder().build();
      List<Card> deck = firstGame.getDeck();
      deck.add(new Card("J", "\u2666"));
    } catch (IllegalArgumentException e) {
      assertEquals("", firstGame.getGameState());
    }
  }

  /**
   * Catching exception and testing is game over when passed invalid deck to start method.
   */
  @Test
  public void testInvalidDeckException2() {
    try {
      firstGame = FreecellModel.getBuilder().build();
      List<Card> deck = firstGame.getDeck();
      deck.add(new Card("J", "\u2666"));
    } catch (IllegalArgumentException e) {
      assertEquals(false, firstGame.isGameOver());
    }
  }

  /**
   * Testing start game called on an already started game.
   */
  @Test
  public void testStartGameOnStartGame() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 6);
    String afterMoves = firstGame.getGameState();
    firstGame.startGame(deck, false);
    String afterReset = firstGame.getGameState();
    assertNotEquals(afterMoves, afterReset);
  }

  /**
   * Testing start game called on an already started game.
   */
  @Test
  public void testStartGameOnStartGame2() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    String afterMoves = firstGame.getGameState();
    firstGame.startGame(sampleDeck, false);
    String afterReset = firstGame.getGameState();
    assertNotEquals(afterMoves, afterReset);
  }

  /**
   * Testing move to open from cascade.
   */
  @Test
  public void testCascadeToOpen() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: 8♣\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n" +
            "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n" +
            "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n" +
            "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♦, K♦\n" +
            "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", firstGame.getGameState());
  }

  /**
   * Testing move to open fails from Foundation.
   */
  @Test(expected = IllegalStateException.class)
  public void testFoundationToOpen2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when open pile already filled.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToOpen2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 4);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail2() {
    firstGame = FreecellModel.getBuilder().opens(1).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to cascade fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail3() {
    firstGame = FreecellModel.getBuilder().opens(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to open fails when source is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSourceEmptyMoveOpen() {

    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    int j = 0;
    for (int i = 0; i < 4; i++) {
      firstGame.move(PileType.CASCADE, i, 12, PileType.FOUNDATION, i);
      if (i == 3) {
        j++;
        if (j < 12) {
          i = -1;
        }
      }
    }
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);

  }

  /**
   * Testing move to cascade fails when source is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSourceEmptyMoveCascade() {

    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    int j = 0;
    for (int a = 0; a < 4; a++) {
      firstGame.move(PileType.CASCADE, a, 12, PileType.FOUNDATION, a);
      if (a == 3) {
        j++;
        if (j < 12) {
          a = -1;
        }
      }
    }
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 2);

  }

  /**
   * Testing move to foundation fails when source is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSourceEmptyMoveOpen2() {

    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    int j = 0;
    for (int b = 0; b < 4; b++) {
      firstGame.move(PileType.CASCADE, b, 12, PileType.FOUNDATION, b);
      if (b == 3) {
        j++;
        if (j < 12) {
          b = -1;
        }
      }
    }
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 2);

  }

  /**
   * Test valid move from open to open passes.
   */
  @Test
  public void openToOpen() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    String afterFirstMove = firstGame.getGameState();
    firstGame.move(PileType.OPEN, 2, 12, PileType.OPEN, 1);
    assertNotEquals(afterFirstMove, firstGame.getGameState());
  }

  /**
   * Test move from open to open same pile fails.
   */
  @Test(expected = IllegalArgumentException.class)
  public void openToOpenFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.OPEN, 2, 12, PileType.OPEN, 2);
  }

  /**
   * Test move from cascade to cascade same pile fails.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cascadeToCascadeFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.CASCADE, 6);
  }

  /**
   * Test move from Foundation to Foundation same pile fails.
   */
  @Test(expected = IllegalStateException.class)
  public void foundationToFoundationMoveFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,0,12,PileType.FOUNDATION,0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Tests move failure to Cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadePile() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.CASCADE, 7);
  }

  /**
   * Tests move from Cascade pile to Open pile.
   */
  @Test
  public void testMoveCascadePile3() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.OPEN, 3);
    firstGame.move(PileType.OPEN, 3, 123, PileType.CASCADE, 5);
    firstGame.move(PileType.CASCADE, 5, 123, PileType.FOUNDATION, 1);
    firstGame.move(PileType.CASCADE, 3, 123, PileType.OPEN, 1);
    firstGame.move(PileType.OPEN, 1, 123, PileType.FOUNDATION, 2);
    firstGame.move(PileType.CASCADE, 3, 123, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2: A♠\n" +
            "F3: A♣\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 3♣\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣", firstGame.getGameState());
  }

  /**
   * Tests move from Cascade pile to Cascade pile.
   */
  @Test
  public void testMoveCascadeCascade() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 6);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: 8♣\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n" +
            "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n" +
            "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n" +
            "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♦, K♦, Q♣\n" +
            "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", firstGame.getGameState());
  }

  /**
   * Testing move to cascade fails from Foundation.
   */
  @Test(expected = IllegalStateException.class)
  public void testFoundationToCascade() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 0);
  }


  /**
   * Testing move to empty cascade pile.
   */
  @Test
  public void testMoveToEmptyCascade() {

    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    int j = 0;
    for (int a = 0; a < 4; a++) {
      firstGame.move(PileType.CASCADE, a, 12, PileType.FOUNDATION, a);
      if (a == 3) {
        j++;
        if (j < 12) {
          a = -1;
        }
      }
    }
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
    assertEquals("F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2:\n" +
            "C3: K♦\n" +
            "C4: K♣", firstGame.getGameState());
  }

  /**
   * Testing invalid move to Cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCascadeMove() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidFoundationMove() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testValidFoundationMoveEmptyFoundation() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
            "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣", firstGame.getGameState());
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testValidFoundationMoveNonEmptyFoundation() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠, 2♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
            "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣", firstGame.getGameState());
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testinvalidFoundationMoveNonEmptyFoundation() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testinvalidFoundationMoveEmptyFoundation() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testIsGameOverFoundationNotEmpty() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertFalse(firstGame.isGameOver());
  }
}
