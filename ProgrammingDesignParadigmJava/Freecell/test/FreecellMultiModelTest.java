import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A Junit test class for the FreecellModel class that implements FreecellOperations Interface.
 */
public class FreecellMultiModelTest {
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
  public void testFreecellBuilderModel() {
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
  public void testFreeCellLineLengthsModel() {
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
  public void testFreecellBuilderWokrsMoreThanEightCascadesModel() {
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
  public void testFreecellBuilderWokrsMoreThanFourOpensModel() {
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
  public void testFreecellBuilderFail2Model() {
    firstGame = FreecellModel.getBuilder().cascades(3).opens(4).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail3Model() {
    firstGame = FreecellModel.getBuilder().cascades(8).opens(0).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail4Model() {
    firstGame = FreecellModel.getBuilder().cascades(3).opens(5).build();
  }

  /**
   * Check if Object is created when legal cascade and open values passed.
   */
  @Test
  public void testFreecellBuilder1Model() {
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
  public void testDeckInvalidModel() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalidssModel() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid2Model() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalidsModel() {
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid3Model() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("11", "\u2660"));
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid4Model() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("10", "\u2662"));
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOverModel() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOverShModel() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when game hasn't begun.
   */
  @Test
  public void testIsGameOverXModel() {
    firstGame = FreecellModel.getBuilder().build();
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1Model() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1ShuModel() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2Model() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2ShuModel() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for true isGameOver.
   */
  @Test
  public void testIsGameOverTrueModel() {

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
  public void testMoveOpenPileModel() {
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
  public void testMoveCascadePile2Model() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.OPEN, 7);
  }

  /**
   * Test to check if exception is thrown when attempting to make a move before game has begun.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveInvalidModel() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
  }

  /**
   * Catching exception and testing game state when passed invalid deck to start method.
   */
  @Test
  public void testInvalidDeckExceptionModel() {
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
  public void testInvalidDeckException2Model() {
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
  public void testStartGameOnStartGameModel() {
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
  public void testStartGameOnStartGameModel2() {
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
  public void testCascadeToOpenModel() {
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
  public void testFoundationToOpenModel2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when open pile already filled.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToOpenModel2() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenModelFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 4);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenModelFail2() {
    firstGame = FreecellModel.getBuilder().opens(1).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to cascade fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenModelFail3() {
    firstGame = FreecellModel.getBuilder().opens(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to open fails when source is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSourceEmptyModelMoveOpen() {

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
  public void testSourceEmptyModelMoveCascade() {

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
  public void testSourceEmptyModelMoveOpen2() {

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
  public void openToOpenModel() {
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
  public void openToOpenModelFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.OPEN, 2, 12, PileType.OPEN, 2);
  }

  /**
   * Test move from cascade to cascade same pile fails.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cascadeToCascadeModelFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.CASCADE, 6);
  }

  /**
   * Test move from Foundation to Foundation same pile fails.
   */
  @Test(expected = IllegalStateException.class)
  public void foundationToFoundationModelMoveFail() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,0,12,PileType.FOUNDATION,0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Tests move failure to Cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testModelMoveCascadePile() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.CASCADE, 7);
  }

  /**
   * Tests move from Cascade pile to Open pile.
   */
  @Test
  public void testModelMoveCascadePile3() {
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
  public void testModelMoveCascadeCascade() {
    firstGame = FreecellModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    firstGame.move(PileType.CASCADE, 6, 3, PileType.OPEN, 2);
    firstGame.move(PileType.CASCADE, 2, 3, PileType.CASCADE, 6);
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
  public void testModelFoundationToCascade() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 0);
  }


  /**
   * Testing move to empty cascade pile.
   */
  @Test
  public void testModelMoveToEmptyCascade() {

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
  public void testModelInvalidCascadeMove() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test(expected = IllegalStateException.class)
  public void testModelInvalidFoundationMove() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testValidFoundationModelMoveEmptyFoundation() {
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
  public void testValidFoundationModelMoveNonEmptyFoundation() {
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
  public void testinvalidFoundationModelMoveNonEmptyFoundation() {
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
  public void testinvalidModelFoundationMoveEmptyFoundation() {
    firstGame = FreecellModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testIsGameOverModelFoundationNotEmpty() {
    firstGame = FreecellModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Check working of Builder for FreecellOperations class.
   */
  @Test
  public void testFreecellBuilder() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    secondGame = FreecellMultiMoveModel.getBuilder().build();
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
    FreecellOperations thirdGame = FreecellMultiMoveModel.getBuilder().cascades(4).opens(3).build();
    thirdGame.startGame(x, false);
    assertNotEquals(firstGame.getGameState(), thirdGame.getGameState());
    FreecellOperations fourthGame = FreecellMultiMoveModel.getBuilder().build();
    fourthGame.startGame(x, true);
    assertNotEquals(firstGame.getGameState(), fourthGame.getGameState());
  }

  /**
   * Checking number of lines for different values of cascade and open pile values.
   */
  @Test
  public void testFreeCellLineLengths() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    secondGame = FreecellMultiMoveModel.getBuilder().build();
    FreecellOperations thirdGame = FreecellMultiMoveModel.getBuilder().cascades(4).opens(3).build();
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
    FreecellOperations fourthGame = FreecellMultiMoveModel.getBuilder()
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(9).opens(4).build();
    secondGame = FreecellMultiMoveModel.getBuilder().cascades(9).opens(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(6).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(3).opens(4).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail3() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(8).opens(0).build();
  }

  /**
   * Check if Object is not created when exception is to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFreecellBuilderFail4() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(3).opens(5).build();
  }

  /**
   * Check if Object is created when legal cascade and open values passed.
   */
  @Test
  public void testFreecellBuilder1() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(8).opens(2).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).opens(1).build();
    assertEquals("", firstGame.getGameState());
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    assertEquals("", firstGame.getGameState());
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalidss() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    sampleDeck.remove(51);
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid2() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, false);
  }

  /**
   * Check if start game fails when passed an invalid deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalids() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    sampleDeck.remove(51);
    sampleDeck.add(new Card("K", "\u2660"));
    firstGame.startGame(sampleDeck, true);
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid3() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("11", "\u2660"));
  }

  /**
   * Check to make sure no invalid card can ever be added to the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeckInvalid4() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    List<Card> x = firstGame.getDeck();
    x.remove(51);
    x.add(new Card("10", "\u2662"));
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOver() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no Cards in the foundation piles.
   */
  @Test
  public void testIsGameOverSh() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when game hasn't begun.
   */
  @Test
  public void testIsGameOverX() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there not 13 Cards in the foundation pile.
   */
  @Test
  public void testIsGameOver1Shu() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for false isGameOver when there is no King on the top of the foundation pile.
   */
  @Test
  public void testIsGameOver2Shu() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, true);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Checks for true isGameOver.
   */
  @Test
  public void testIsGameOverTrue() {

    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.OPEN, 7);
  }

  /**
   * Test to check if exception is thrown when attempting to make a move before game has begun.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveInvalid() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
  }

  /**
   * Catching exception and testing game state when passed invalid deck to start method.
   */
  @Test
  public void testInvalidDeckException() {
    try {
      firstGame = FreecellMultiMoveModel.getBuilder().build();
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
      firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when open pile already filled.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToOpen2() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 4);
  }

  /**
   * Testing move to open fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail2() {
    firstGame = FreecellMultiMoveModel.getBuilder().opens(1).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to cascade fails when invalid index of open pile passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenFail3() {
    firstGame = FreecellMultiMoveModel.getBuilder().opens(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, -1);
  }

  /**
   * Testing move to open fails when source is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSourceEmptyMoveOpen() {

    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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

    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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

    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.OPEN, 2, 12, PileType.OPEN, 2);
  }

  /**
   * Test move from cascade to cascade same pile fails.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cascadeToCascadeFail() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(firstGame.getDeck(), false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.CASCADE, 6);
  }

  /**
   * Test move from Foundation to Foundation same pile fails.
   */
  @Test(expected = IllegalStateException.class)
  public void foundationToFoundationMoveFail() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,0,12,PileType.FOUNDATION,0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Tests move failure to Cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadePile() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 123, PileType.CASCADE, 7);
  }

  /**
   * Tests move from Cascade pile to Open pile.
   */
  @Test
  public void testMoveCascadePile3() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    List<Card> deck = firstGame.getDeck();
    firstGame.startGame(deck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.OPEN, 2);
    firstGame.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 6);
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 0);
  }


  /**
   * Testing move to empty cascade pile.
   */
  @Test
  public void testMoveToEmptyCascade() {

    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 0);
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidFoundationMove() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testValidFoundationMoveEmptyFoundation() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
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
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 6, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Testing invalid move to Foundation pile.
   */
  @Test
  public void testIsGameOverFoundationNotEmpty() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(4).build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    firstGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertFalse(firstGame.isGameOver());
  }

  /**
   * Test multiple moves from cascade to cascade.
   */
  @Test
  public void testMultipleMoves() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(5).build();
    firstGame.startGame(sampleDeck,false);

    firstGame.move(PileType.CASCADE,2,12,PileType.OPEN,2);
    firstGame.move(PileType.CASCADE,4,8,PileType.CASCADE,2);
    firstGame.move(PileType.CASCADE,2,8,PileType.CASCADE,4);
    firstGame.move(PileType.CASCADE,0,10,PileType.OPEN,1);
    firstGame.move(PileType.CASCADE,0,8,PileType.CASCADE,2);
    firstGame.move(PileType.CASCADE,1,10,PileType.CASCADE,2);
    firstGame.move(PileType.CASCADE,3,9,PileType.CASCADE,1);

    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: A♦\n" +
            "O3: 2♣\n" +
            "O4:\n" +
            "C1: K♠, Q♥, J♦, 10♣, 8♠, 7♥, 6♦, 5♣\n" +
            "C2: K♥, Q♦, J♣, 9♠, 8♥, 7♦, 6♣, 4♠, 3♥, 2♦, A♠\n" +
            "C3: K♦, Q♣, 10♠, 9♥, 8♦, 7♣, 5♠, 4♥, 3♠, 2♥, A♣\n" +
            "C4: K♣, J♠, 10♥, 9♦, 8♣, 6♠, 5♥, 4♦, 3♣\n" +
            "C5: Q♠, J♥, 10♦, 9♣, 7♠, 6♥, 5♦, 4♣, 3♦, 2♠, A♥",firstGame.getGameState());
  }

  /**
   * Test invalid multiple moves at one go from one cascade pile to another.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveBeforeGame() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.move(PileType.CASCADE,3,8,PileType.CASCADE,6);
  }

  /**
   * Test invalid multiple moves at one go from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPileNumber1() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,-1,12,PileType.CASCADE,8);
  }

  /**
   * Test invalid multiple moves at one go from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPileNumber2() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,0,12,PileType.CASCADE,-1);
  }

  /**
   * Test invalid card index passed when making multiple moves at one go from one cascade pile to
   * another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndex() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,0,-1,PileType.CASCADE,4);
  }

  /**
   * Test invalid source pile index passed when making multiple moves at one go
   * from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSourceIndex() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,9,10,PileType.CASCADE,4);
  }

  /**
   * Test invalid source pile index passed when making multiple moves at one go
   * from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSourceIndex2() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,8,10,PileType.CASCADE,4);
  }

  /**
   * Test invalid source pile index passed when making multiple moves at one go
   * from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSourceIndex3() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,7,10,PileType.CASCADE,9);
  }

  /**
   * Test invalid source pile index passed when making multiple moves at one go
   * from one cascade pile to another.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSourceIndex4() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck, false);
    firstGame.move(PileType.CASCADE,7,10,PileType.CASCADE,8);
  }

  /**
   * Test invalid card index passed to move function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndex4() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,4,8,PileType.CASCADE,4);
  }

  /**
   * Test invalid card index passed to move function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndex5() {
    firstGame = FreecellMultiMoveModel.getBuilder().build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,4,9,PileType.CASCADE,4);
  }

  /**
   * Test move when there aren't enough empty piles while performing multiple moves at one go.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPiles() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(26).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,16,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,18,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,23,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,13,1,PileType.CASCADE,8);
  }

  /**
   * Test move when there aren't enough empty piles while performing multiple moves at one go.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPiles2() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(26).opens(1).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,16,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,18,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,23,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,16,0,PileType.OPEN,0);
    firstGame.move(PileType.CASCADE,13,1,PileType.CASCADE,8);
  }

  /**
   * Test move when there are enough empty piles while performing multiple moves at one go.
   */
  @Test
  public void testValidMultiMove() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(26).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,16,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,18,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,23,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,16,0,PileType.OPEN,0);
    firstGame.move(PileType.CASCADE,13,1,PileType.CASCADE,8);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2:\n" +
            "C1: K♠, 7♦\n" +
            "C2: K♥, 7♣\n" +
            "C3: K♦, 6♠\n" +
            "C4: K♣, 6♥\n" +
            "C5: Q♠, 6♦\n" +
            "C6: Q♥, 6♣\n" +
            "C7: Q♦, 5♠\n" +
            "C8: Q♣, 5♥\n" +
            "C9: J♠, 5♦, 4♣, 3♦, 2♠, A♥\n" +
            "C10: J♥, 5♣\n" +
            "C11: J♦, 4♠\n" +
            "C12: J♣, 4♥\n" +
            "C13: 10♠, 4♦\n" +
            "C14: 10♥\n" +
            "C15: 10♦, 3♠\n" +
            "C16: 10♣, 3♥\n" +
            "C17:\n" +
            "C18: 9♥, 3♣\n" +
            "C19: 9♦\n" +
            "C20: 9♣, 2♥\n" +
            "C21: 8♠, 2♦\n" +
            "C22: 8♥, 2♣\n" +
            "C23: 8♦, A♠\n" +
            "C24: 8♣\n" +
            "C25: 7♠, A♦\n" +
            "C26: 7♥, A♣",firstGame.getGameState());
  }

  /**
   * Test move when there are enough empty piles while performing multiple moves at one go.
   */
  @Test
  public void testValidMultiMove2() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(26).opens(1).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,16,1,PileType.CASCADE,13);
    firstGame.move(PileType.CASCADE,16,0,PileType.OPEN,0);
    firstGame.move(PileType.CASCADE,13,1,PileType.CASCADE,8);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "C1: K♠, 7♦\n" +
            "C2: K♥, 7♣\n" +
            "C3: K♦, 6♠\n" +
            "C4: K♣, 6♥\n" +
            "C5: Q♠, 6♦\n" +
            "C6: Q♥, 6♣\n" +
            "C7: Q♦, 5♠\n" +
            "C8: Q♣, 5♥\n" +
            "C9: J♠, 5♦, 4♣, 3♦\n" +
            "C10: J♥, 5♣\n" +
            "C11: J♦, 4♠\n" +
            "C12: J♣, 4♥\n" +
            "C13: 10♠, 4♦\n" +
            "C14: 10♥\n" +
            "C15: 10♦, 3♠\n" +
            "C16: 10♣, 3♥\n" +
            "C17:\n" +
            "C18: 9♥, 3♣\n" +
            "C19: 9♦, 2♠\n" +
            "C20: 9♣, 2♥\n" +
            "C21: 8♠, 2♦\n" +
            "C22: 8♥, 2♣\n" +
            "C23: 8♦, A♠\n" +
            "C24: 8♣, A♥\n" +
            "C25: 7♠, A♦\n" +
            "C26: 7♥, A♣",firstGame.getGameState());
  }

  /**
   * Test multiple moves from cascade pile to another fails when passing an invalid card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMove1() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE, 0, 8, PileType.CASCADE, 3);
  }

  /**
   * Test multiple moves from cascade pile to another passes when passing a correct card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidMultiMove1() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE, 0, 7, PileType.CASCADE, 3);
  }

  /**
   * Test multiple moves from cascade pile to another fails when passing an incorrect card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMove2() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 3);
  }

  /**
   * Test multiple moves from cascade pile to another fails when passing an incorrect card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMove3() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE, 0, -1, PileType.CASCADE, 3);
  }

  /**
   * Test multiple moves when cards index of source doesn't make a valid build with the top card in
   * destination pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBuildMultiMove1() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(7).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE, 0, 5, PileType.CASCADE, 6);
  }

  /**
   * Test multiple moves when card index of source pile makes a valid build with the top card in
   * destination pile, but the cards that follow don't.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBuildMultiMove2() {
    firstGame = FreecellMultiMoveModel.getBuilder().cascades(14).opens(2).build();
    firstGame.startGame(sampleDeck,false);
    firstGame.move(PileType.CASCADE,0,12,PileType.OPEN,0);
    firstGame.move(PileType.CASCADE,6,3,PileType.CASCADE,4);
    firstGame.move(PileType.CASCADE,4,2,PileType.CASCADE,0);
  }



}
