import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.MockModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FreecellControllerTest {
  IFreecellController controller;
  FreecellOperations<Card> secondGame;
  List<Card> sampleDeck;
  FreecellOperations<Card> firstGame;
  Readable readable;
  Appendable appendable;

  /**
   * This is a setUp block which sets up the deck which is being used in most of the tests.
   */
  @Before
  public void setUp() {
    sampleDeck = new ArrayList<>();
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
   * Passes a null deck in the controller method to check if it is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test() {
    readable = new StringReader("");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(null, firstGame, true);
  }

  /**
   * Passes a null model in the controller method to check if it is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test1() {
    readable = new StringReader("");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, null, true);
  }

  /**
   * Passes a null readable object to see if it is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test2() {
    readable = null;
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, true);
  }

  /**
   * Passes a null appendable object to see if it is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test3() {
    readable = new StringReader("");
    appendable = null;
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, true);
  }

  /**
   * Test if input q works with controller as expected.
   */
  @Test
  public void test4() {
    readable = new StringReader("q");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * checks if q works after 1 move.
   */
  @Test
  public void test5() {
    readable = new StringReader("C1 6 F1\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣" +
            "\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Tests for q in destination to see if it works as expected and quits the game.
   */
  @Test
  public void test6() {
    readable = new StringReader("C1 1 q1");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Tests for q in the card index and checks if it quits as expected.
   */
  @Test
  public void test7() {
    readable = new StringReader("C1 q C7");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Takes q input for source Pile and checks if it quits as expected.
   */
  @Test
  public void test8() {
    readable = new StringReader("q1 7 C7");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Takes an extra card input to the sample deck to see if it works as expected. (removes the
   * duplicate card before starting the game).
   */
  @Test
  public void test9() {
    readable = new StringReader("Cq 7 C7");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.add(new Card("2", "\u2663"));
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Testing if move from cascade to open works as expected.
   */
  @Test
  public void test10() {
    readable = new StringReader("C1 7 O1\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Tests if passing true in shuffle for controller returns a shuffled deck as expected.
   */
  @Test
  public void test11() {
    readable = new StringReader("q");
    Readable readable1 = new StringReader("q");
    appendable = new StringBuffer();
    Appendable appendable1 = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    secondGame = FreecellModel.getBuilder().build();
    IFreecellController controller1 = new FreecellController(readable1, appendable1);
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck.add(new Card("2", "\u2663"));
    controller.playGame(sampleDeck, firstGame, false);
    controller1.playGame(sampleDeck, secondGame, true);
    assertNotEquals(appendable1.toString(), appendable.toString());
  }

  /**
   * Tests if upon invalid input a new input is prompted as expected.
   */
  @Test
  public void test12() {
    readable = new StringReader("A1\nC1 7 O1\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * tests if invalid move works as expected and prompts user to make another input.
   */
  @Test
  public void test13() {
    readable = new StringReader("C1 7 C1\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "Invalid move. Try again.Invalid move to Cascade pile\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Passing an empty deck to see if it is handled as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test14() {
    readable = new StringReader("C1 7 C1\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    sampleDeck = new ArrayList<>();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("", appendable.toString());
  }

  /**
   * test if appendable fails and gives the appropriate illegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void test17() throws IOException {
    readable = new StringReader("C1 7 C1\nq");
    File file = new File("C:\\Users\\keviv\\Documents\\CS5010\\projects\\freecell2\\new.txt");
    appendable = new FileWriter(file);
    ((FileWriter) appendable).close();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("", appendable.toString());
  }

  /**
   * Test to check if behaviour is as expected when there is invalid input.
   */
  @Test
  public void test15() {
    readable = new StringReader("C4 a 7 C6\nq");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();

    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, A♣\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Test if CheckIsNext works properly.
   */
  @Test(expected = IllegalStateException.class)
  public void test16() {
    readable = new StringReader("C4 a 7 C6");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();

    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, A♣\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.", appendable.toString());
  }

  /**
   * Tests using a possible mock Model.
   */
  @Test
  public void testMockModel() {
    readable = new StringReader("C1 7 C1\nq");
    appendable = new StringBuffer();
    StringBuilder log = new StringBuilder();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    FreecellOperations game = new MockModel(log);
    controller.playGame(sampleDeck, game, false);
    assertEquals("\nGame Started with [K♠, K♥, K♦, K♣, Q♠, Q♥, Q♦, Q♣, J♠, J♥, J♦, J♣," +
            " 10♠, 10♥, 10♦, 10♣, 9♠, 9♥, 9♦, 9♣, 8♠, 8♥, 8♦, 8♣, 7♠, 7♥, 7♦, 7♣," +
            " 6♠, 6♥, 6♦, 6♣, 5♠, 5♥, 5♦, 5♣, 4♠, 4♥, 4♦, 4♣, 3♠, 3♥, 3♦, 3♣," +
            " 2♠, 2♥, 2♦, 2♣, A♠, A♥, A♦, A♣] and false\n" +
            "Game state was retrieved\n" +
            "Move attempted: CASCADE0 Index: 6 to Destination CASCADE0\n" +
            "Game state was retrieved\n" +
            "Checked if the game is over or not", ((MockModel) game).getLog());
  }

  /**
   * Testing if when game is over it returns data as expected.
   */
  @Test
  public void testGameOver() {
    readable = new StringReader("C1 6 F1\nC2 6 F2\nC3 6 F3\nC4 6 F4\n" +
            "C5 5 F1\nC6 5 F2\nC7 5 F3\nC8 5 F4\n" +
            "C1 5 F1\nC2 5 F2\nC3 5 F3\nC4 5 F4\n" +
            "C1 5 F1\nC2 5 F2\nC3 5 F3\nC4 5 F4\n" +
            "C5 4 F1\nC6 4 F2\nC7 4 F3\nC8 4 F4\n" +
            "C1 4 F1\nC2 4 F2\nC3 4 F3\nC4 4 F4\n" +
            "C5 3 F1\nC6 3 F2\nC7 3 F3\nC8 3 F4\n" +
            "C1 3 F1\nC2 3 F2\nC3 3 F3\nC4 3 F4\n" +
            "C5 2 F1\nC6 2 F2\nC7 2 F3\nC8 2 F4\n" +
            "C1 2 F1\nC2 2 F2\nC3 2 F3\nC4 2 F4\n" +
            "C5 1 F1\nC6 1 F2\nC7 1 F3\nC8 1 F4\n" +
            "C1 1 F1\nC2 1 F2\nC3 1 F3\nC4 1 F4\n" +
            "C5 0 F1\nC6 0 F2\nC7 0 F3\nC8 0 F4\n" +
            "C1 0 F1\nC2 0 F2\nC3 0 F3\nC4 0 F4\n");
    appendable = new StringBuffer();
    controller = new FreecellController(readable, appendable);
    firstGame = FreecellModel.getBuilder().build();
    controller.playGame(sampleDeck, firstGame, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠\n" +
            "F2: A♥\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠\n" +
            "F2: A♥\n" +
            "F3: A♦\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠\n" +
            "F2: A♥\n" +
            "F3: A♦\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠, 2♠\n" +
            "F2: A♥\n" +
            "F3: A♦\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠, 2♠\n" +
            "F2: A♥, 2♥\n" +
            "F3: A♦\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠, 2♠\n" +
            "F2: A♥, 2♥\n" +
            "F3: A♦, 2♦\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "F1: A♠, 2♠\n" +
            "F2: A♥, 2♥\n" +
            "F3: A♦, 2♦\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠\n" +
            "F2: A♥, 2♥\n" +
            "F3: A♦, 2♦\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠\n" +
            "F2: A♥, 2♥, 3♥\n" +
            "F3: A♦, 2♦\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠\n" +
            "F2: A♥, 2♥, 3♥\n" +
            "F3: A♦, 2♦, 3♦\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠\n" +
            "F2: A♥, 2♥, 3♥\n" +
            "F3: A♦, 2♦, 3♦\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "Invalid move. Try again.Invalid move to Foundation pile" +
            "Invalid move. Try again.Invalid move to Foundation pile" +
            "Invalid move. Try again.Invalid move to Foundation pile" +
            "Invalid move. Try again.Invalid move to Foundation pile" +
            "F1: A♠, 2♠, 3♠, 4♠\n" +
            "F2: A♥, 2♥, 3♥\n" +
            "F3: A♦, 2♦, 3♦\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥\n" +
            "F3: A♦, 2♦, 3♦\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠, 6♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦, 6♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠, 7♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥, 7♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦, 7♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠, 8♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥, 8♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦, 8♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠, 9♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥, 9♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦, 9♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠, 10♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥, 10♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣, 10♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, J♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥, J♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦, J♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5: Q♠\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6: Q♥\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7: Q♦\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8: Q♣\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: K♥\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3: K♦\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n\n" +
            "Game over.", appendable.toString());
  }

}

