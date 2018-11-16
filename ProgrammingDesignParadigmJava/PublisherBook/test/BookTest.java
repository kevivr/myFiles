import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book gameOfThrones;

  /**
   *  Construct sample Objects to test Book Class.
   */
  @Before
  public void setUp() {

    Person rrMartin;
    rrMartin = new Person("R.R", "Martin", 1949);
    gameOfThrones = new Book("Game Of Thrones", rrMartin, 50.99F);
  }

  @Test
  public void testTitle() {
    assertEquals(gameOfThrones.getTitle(), "Game Of Thrones");
  }

  @Test
  public void testAuthor() {
    assertEquals(gameOfThrones.getAuthor().getFirstName(), "R.R");
    assertEquals(gameOfThrones.getAuthor().getLastName(), "Martin");
    assertEquals(gameOfThrones.getAuthor().getYearOfBirth(), 1949);
  }

  @Test
  public void testPrice() {
    assertEquals(gameOfThrones.getPrice(), 50.99F, 0.01);
  }


}
