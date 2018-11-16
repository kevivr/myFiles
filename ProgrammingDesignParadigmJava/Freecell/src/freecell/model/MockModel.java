package freecell.model;

import java.util.ArrayList;
import java.util.List;

public class MockModel implements FreecellOperations {
  private StringBuilder log;


  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public List getDeck() {
    log.append("\nDeck Retrieved");
    return new ArrayList();
  }

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    log.append("\nGame Started with " + deck + " and " + shuffle);
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
          throws IllegalArgumentException, IllegalStateException {
    log.append("\nMove attempted: " + source + pileNumber +
            " Index: " + cardIndex + " to Destination " + destination + destPileNumber);
  }

  @Override
  public boolean isGameOver() {
    log.append("\nChecked if the game is over or not");
    return false;
  }

  @Override
  public String getGameState() {
    log.append("\nGame state was retrieved");
    return "";
  }

  public String getLog() {
    return log.toString();
  }
}
