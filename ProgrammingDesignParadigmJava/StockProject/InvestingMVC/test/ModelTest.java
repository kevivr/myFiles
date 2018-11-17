import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import investormodel.User;

import static org.junit.Assert.assertEquals;

public class ModelTest {
  private User sampleUser;

  /**
   * Static method to write an investor object to a binary file so state of the object can be
   * reloaded.
   */
  private static void writeToFile(User user) throws IOException {
    ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(new FileOutputStream("Investor.bin"));

    objectOutputStream.writeObject(user);
  }

  /**
   * Static method to read an investor object from a binary file.
   */
  private static User readFile() throws IOException, ClassNotFoundException {
    ObjectInputStream objectInputStream =
            new ObjectInputStream(new FileInputStream("Investor.bin"));

    User user = (User) objectInputStream.readObject();
    return user;
  }

  /**
   * Construct sample user/investor to be used throughout to test Investor Model.
   */
  @Before
  public void setUp() {
    try {
      sampleUser = readFile();
    } catch (ClassNotFoundException | IOException e) {
      sampleUser = new User();
    }
  }

  /**
   * Check Creating a new Portfolio fails when portfolio already exists. (Test2)
   */
  @Test
  public void createNewPortfolio2() {
    try {
      writeToFile(sampleUser);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check Creating a new Portfolio fails when portfolio entered is null. (Test3)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolio3() {
    sampleUser.createPortFolio(null);
  }

  /**
   * Check Creating a new Portfolio fails when portfolio entered is empty. (Test4)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolio4() {
    sampleUser.createPortFolio("");
  }

  /**
   * Check buying Stock successful. (Test5)
   */
  @Test
  public void createNewStockForNewPortFolio() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 120,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-09"));
    sampleUser.buyStock("myPortfolio2", "AAPL", 120,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-09"));
    assertEquals("Portfolio cost basis: $13148",
            sampleUser.getPortfolioCostBasis("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-14")));
    assertEquals("MSFT: $13148 Number of Stocks : 120\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-14")));
  }

  /**
   * Check buying Stock unsuccessful when passed an invalid value of date. (Test6)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewStockForNewPortFolio2() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 120,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-16"));
  }

  /**
   * Check buying Stock unsuccessful when passed an invalid value of count. (Test7)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewStock3() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", -3,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-01"));
  }

  /**
   * Check buying Stock unsuccessful when passed an invalid value of count. (Test8)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewStock4() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 0,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-01"));
  }

  /**
   * Check buying Stock unsuccessful when passed a random stock ticker that doesn't exist. (Test9)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewStock5() throws ParseException {
    sampleUser.buyStock("myPortfolio", "AAAA", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
  }

  /**
   * Check buying Stock unsuccessful when passed a random stock ticker that doesn't exist. (Test10)
   */
  @Test(expected = IllegalArgumentException.class)
  public void createNewStock6() throws ParseException {
    sampleUser.buyStock("myPortfolio", "AAAA", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
  }

  /**
   * Check buying Stock when portfolio passed doesn't exist creates one and then adds stock to it.
   * (Test11)
   */
  @Test
  public void createNewStock7() throws ParseException {
    sampleUser.buyStock("PortfolioNew", "MSFT", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    assertEquals("[NewPortfolio, NewPortfolio2, PortfolioNew]",
            sampleUser.getAllPortfolio());
    assertEquals("MSFT: $13807 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("PortfolioNew",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-14")));
  }

  /**
   * Check buying Shares of the same Stock but different dates is possible. (Test12)
   */
  @Test
  public void createNewStock8() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    assertEquals("MSFT: $13807 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-14")));
    assertEquals("MSFT: $13807 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-14")));
  }

  /**
   * Check buying Shares of the same Stock same dates is possible. (Test13)
   */
  @Test
  public void createNewStock9() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    assertEquals("MSFT: $13807 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-14")));
    sampleUser.buyStock("myPortfolio", "MSFT", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    assertEquals("MSFT: $27615 Number of Stocks : 246\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-14")));
  }

  /**
   * Check buying Shares of new stock works properly.  (Test14)
   */
  @Test
  public void createNewStock10() throws ParseException {
    sampleUser.buyStock("myPortfolio", "MSFT", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    sampleUser.buyStock("myPortfolio", "AAPL", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    sampleUser.buyStock("myPortfolio", "GOOG", 123,
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
    assertEquals("MSFT: $13807 Number of Stocks : 123\n" +
                    "GOOG: $140074 Number of Stocks : 123\n" +
                    "AAPL: $27905 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("myPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-14")));
  }

  /**
   * Check if cost basis and value by stocks for a portfolio are returned properly. (Test15)
   */
  @Test
  public void testCostBasisByStock() throws ParseException {
    assertEquals("MSFT: $18787 Number of Stocks : 243\n" +
                    "GOOG: $202123 Number of Stocks : 246\n" +
                    "AAPL: $12425 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-12")));
    assertEquals("MSFT: $5639 Number of Stocks : 123\n" +
                    "GOOG: $68988 Number of Stocks : 123\n" +
                    "AAPL: $12425 Number of Stocks : 123\n",
            sampleUser.getPortfolioCostBasisByStocks("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for a portfolio are returned properly. (Test16)
   */
  @Test
  public void testValueByStock() throws ParseException {
    assertEquals("MSFT: $25758 Number of Stocks : 243\n" +
                    "GOOG: $255348 Number of Stocks : 246\n" +
                    "AAPL: $23862 Number of Stocks : 123\n",
            sampleUser.getPortfolioValueByStocks("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-12")));
    assertEquals("MSFT: $5535 Number of Stocks : 123\n" +
                    "GOOG: $66912 Number of Stocks : 123\n" +
                    "AAPL: $12423 Number of Stocks : 123\n",
            sampleUser.getPortfolioValueByStocks("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test17)
   */
  @Test
  public void testValueByStock1() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("There are no Stocks in this portfolio",
            sampleUser.getPortfolioCostBasisByStocks("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test18)
   */
  @Test
  public void testValueByStock2() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("This portfolio is empty",
            sampleUser.getPortfolioValueByStocks("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test19)
   */
  @Test
  public void testValueByStock3() throws ParseException {
    assertEquals("There are no Stocks in this portfolio",
            sampleUser.getPortfolioCostBasisByStocks("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test20)
   */
  @Test
  public void testValueByStock4() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("This portfolio is empty",
            sampleUser.getPortfolioValueByStocks("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for a portfolio are returned properly. (Test21)
   */
  @Test
  public void testCostBasis() throws ParseException {
    assertEquals("Portfolio cost basis: $233335",
            sampleUser.getPortfolioCostBasis("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-12")));
    assertEquals("Portfolio cost basis: $87052",
            sampleUser.getPortfolioCostBasis("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for a portfolio are returned properly. (Test22)
   */
  @Test
  public void testValue() throws ParseException {
    assertEquals("Portfolio value: $304968",
            sampleUser.getPortfolioTotalValue("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-12")));
    assertEquals("Portfolio value: $84870",
            sampleUser.getPortfolioTotalValue("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test23)
   */
  @Test
  public void testValue1() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("This portfolio is empty",
            sampleUser.getPortfolioCostBasis("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test24)
   */
  @Test
  public void testValue2() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("This portfolio is empty",
            sampleUser.getPortfolioTotalValue("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test25)
   */
  @Test
  public void testValue3() throws ParseException {
    assertEquals("Portfolio cost basis: $0",
            sampleUser.getPortfolioCostBasis("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test26)
   */
  @Test
  public void testValue4() throws ParseException {
    sampleUser.createPortFolio("medical");
    assertEquals("This portfolio is empty",
            sampleUser.getPortfolioTotalValue("medical",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-12")));
  }

  /**
   * Check if cost basis and value by stocks for an empty portfolio are returned properly. (Test27)
   */
  @Test
  public void testValue5() throws ParseException {
    assertEquals("Portfolio value: $0",
            sampleUser.getPortfolioTotalValue("NewPortfolio",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-12")));
  }

  /**
   * Check get all portfolio method for a user that doesn't have any portfolios.
   */
  @Test
  public void testAllPortfolio() throws ParseException {
    User user = new User();
    assertEquals("No Portfolios yet", user.getAllPortfolio());
  }
}
