package freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


import freecell.model.Card;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

/**
 * An implementation of the free cell game controller. Class has appendable and readable objects as
 * instance variables to allow various ways of taking inputs and giving outputs. (Eg. through files,
 * through Strings, through console etc). Implements the IFreecellController interface
 * and all its methods.
 */
public class FreecellController implements IFreecellController<Card> {
  private final Readable in;
  private final Appendable out;

  /**
   * Constructor for a FreecellController object that can be used to control a freecell model.
   *
   * @param in  A Readable object to read input from.
   * @param out An appendable object to write output to.
   */
  public FreecellController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable or Appendable object passed is null");
    }
    this.in = in;
    this.out = out;
  }

  /**
   * Start and play a new game of freecell with the provided deck. This deck should be used as-is.
   * This method returns only when the game is over (either by winning or by quitting)
   *
   * @param deck    the deck to be used to play this game
   * @param model   the model for the game
   * @param shuffle shuffle the deck if true, false otherwise
   * @throws IllegalArgumentException if the deck is null or invalid, or if the model is null
   * @throws IllegalStateException    if the controller is unable to read input or transmit output
   */
  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, boolean shuffle)
          throws IllegalArgumentException, IllegalStateException {
    // Throw IllegalargumentException if Deck or model is invalid or null.
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Deck or model invalid");
    }
    try {
      model.startGame(deck, shuffle);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Deck or model invalid");
    }

    PileType source;
    PileType destination;
    int index;
    int sourcePileNumber;
    int destPileNumber;
    Scanner scan = new Scanner(this.in);

    //On starting game successfully, append the game state to the output.
    try {
      this.out.append(model.getGameState() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable or Readable failed");
    }

    //Make moves till input exists or is valid.
    while (true) {
      try {
        String s;
        s = scan.next();
        //Get source pile type.
        try {
          source = getPileType(scan, s);
        } catch (IllegalArgumentException e) {
          this.out.append("\nGame quit prematurely.");
          return;
        }

        //Get source pile number.
        try {
          sourcePileNumber = getNumberFromScanner(scan, s.substring(1)) - 1;
        } catch (IllegalArgumentException e) {
          this.out.append("\nGame quit prematurely.");
          return;
        }

        s = scan.next();

        // Get card index.
        try {
          index = getNumberFromScanner(scan, s) - 1;
        } catch (IllegalArgumentException e) {
          this.out.append("\nGame quit prematurely.");
          return;
        }

        s = scan.next();

        // Get desination pile type.
        try {
          destination = getPileType(scan, s);
        } catch (IllegalArgumentException e) {
          this.out.append("\nGame quit prematurely.");
          return;
        }

        // Get destination pile number.
        try {
          destPileNumber = getNumberFromScanner(scan, s.substring(1)) - 1;
        } catch (IllegalArgumentException e) {
          this.out.append("\nGame quit prematurely.");
          return;
        }

        // Perform move on receiving valid parameters.
        try {
          model.move(source, sourcePileNumber, index, destination, destPileNumber);
          this.out.append(model.getGameState() + "\n");
        } catch (IllegalArgumentException | IllegalStateException e) {
          this.out.append("Invalid move. Try again." + e.getMessage());
          continue;
        }

        // Quit game if received relevant status from model.
        if (model.isGameOver()) {
          this.out.append("\nGame over.");
          return;
        }
      } catch (IOException | NoSuchElementException e) {
        throw new IllegalStateException("Appendable or Readable Failed");
      }
    }
  }

  /**
   * Helper method to check if the String passed is 'q' or 'Q' in which case the control quits the
   * game.
   *
   * @param s the String to perform the check on.
   * @return true if the String is 'q' or 'Q'.
   */
  private boolean checkIsStringQuit(String s) {
    return (s.contains("q") || s.contains("Q"));
  }

  /**
   * Helper method to return the type of pile i.e Cascade, Foundation or Open based on the input.
   *
   * @param scan The text scanner that contains the input.
   * @param s    The string to look for the pile type in.
   * @return the Piletype according the the string passed.
   * @throws IllegalArgumentException if 'q' or 'Q is the String entered.
   */
  private PileType getPileType(Scanner scan, String s) {
    while (true) {
      switch (s.charAt(0)) {
        case 'C':
          return PileType.CASCADE;
        case 'F':
          return PileType.FOUNDATION;
        case 'O':
          return PileType.OPEN;
        default:
          if (checkIsStringQuit(s)) {
            throw new IllegalArgumentException("q or Q hit, will quit");
          }
          s = scan.next();
      }
    }
  }

  /**
   * Helper method to return the int value (card index or pile index) based on the input.
   *
   * @param scan The text scanner that contains the input.
   * @param s    The string to look for the number in.
   * @return the int value of the string passed.
   * @throws IllegalArgumentException if 'q' or 'Q' is the String entered.
   */
  private int getNumberFromScanner(Scanner scan, String s) {
    while (true) {
      try {
        return Integer.parseInt(s);
      } catch (NumberFormatException e) {
        if (checkIsStringQuit(s)) {
          throw new IllegalArgumentException("q or Q hit, will quit");
        }
        s = scan.next();
      }
    }
  }
}