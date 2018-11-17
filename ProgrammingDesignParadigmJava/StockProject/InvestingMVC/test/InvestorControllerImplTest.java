import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import investorcontroller.InvestorControllerImpl;
import investormodel.Investor;
import investormodel.MockModel;
import investormodel.User;
import investorview.InvestorView;

import static org.junit.Assert.assertEquals;

public class InvestorControllerImplTest {
  /**
   * Test check for null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test1() {
    Investor model = null;
    Readable readable = new StringReader("1\nShivam\n7\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("", appendable.toString());
  }

  /**
   * Test check for null readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test2() {
    Investor model = new User();
    Readable readable = null;
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("", appendable.toString());
  }

  /**
   * Test check for null appendable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test3() {
    Investor model = new User();
    Readable readable = new StringReader("1\nShivam\n7\n");
    Appendable appendable = null;
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("", appendable.toString());
  }

  /**
   * Test check for null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test4() {
    Investor model = new User();
    Readable readable = new StringReader("1\nShivam\n7\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = null;
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("", appendable.toString());
  }

  /**
   * Test Startup and exit work as expected with proper outputs.
   */
  @Test
  public void test5() {
    Investor model = new User();
    Readable readable = new StringReader("8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Tests the creating of a portfolio.
   */
  @Test
  public void test6() {
    Investor model = new User();
    Readable readable = new StringReader("1\nPortfolio\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Creating a Portfolio and using option 6 to display all the portfolios for a user.
   */
  @Test
  public void test7() {
    Investor model = new User();
    Readable readable = new StringReader("1\nCollege Portfolio\n6\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "[College Portfolio]\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Test to see the functioning of calling the startApplication function multiple times.
   */
  @Test
  public void test8() {
    Investor model = new User();
    Readable readable = new StringReader("8\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Testing the second option without buying a stock and checking if result is as expected.
   */
  @Test
  public void test9() {
    Investor model = new User();
    Readable readable =
            new StringReader("1\nCollege Portfolio\n2\nCollege Portfolio\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "There are no Stocks in this portfolio\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * testing the menu option 2 after buying a stock and seeing the data is being passed between the
   * model and the controller as expected.
   */
  @Test
  public void test10() {
    Investor model = new User();
    Readable readable =
            new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-13\n10\n\n2\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "GOOG: $10360 Number of Stocks : 10\n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Testing the menu option 5 after the buying of a stock and seeing the data is being passed to
   * the model as expected.
   */
  @Test
  public void test11() {
    Investor model = new User();
    Readable readable =
            new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-13\n10\n\n5\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Portfolio cost basis: $10360\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Test the controller option of 7 after buying a stock to see the data is being passed to the
   * model as expected.
   */
  @Test
  public void test12() {
    Investor model = new User();
    Readable readable =
            new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-13\n10\n\n7\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Portfolio value: $10360\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Testing the controller with the menu option 3 after buying a stock and seeing that it returns
   * the value as expected.
   */
  @Test
  public void test13() {
    Investor model = new User();
    Readable readable =
            new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-13\n10\n\n3\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "GOOG: $10360 Number of Stocks : 10\n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test with a mock model which displays that the information being passed from the controller is
   * reaching the model as expected.
   */
  @Test
  public void test14() {
    Investor model = new MockModel();
    Readable readable = new StringReader("1\nC\n" +
            "4\nC\nGOOG\nn\n2018-11-13\n10\n\n" +
            "2\nC\nn\n2018-11-13\n\n" +
            "5\nC\nn\n2018-11-13\n\n" +
            "7\nC\nn\n2018-11-13\n\n" +
            "3\nC\nn\n2018-11-13\n\n" +
            "6\n\n" +
            "8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("Portfolio of name C created\n" +
            "Stock for company GOOG bought for portfolio C on Tue Nov 13 00:00:00 EST" +
            " 2018 number of stocks 10\n" +
            "Cost Basis per stock for C on date: Tue Nov 13 00:00:00 EST 2018\n" +
            "Cost Basis for C on date: Tue Nov 13 00:00:00 EST 2018\n" +
            "Value for C on date: Tue Nov 13 00:00:00 EST 2018\n" +
            "Stocks of the portfolio C viewed for value on Date: Tue Nov 13 00:00:00 EST 2018\n" +
            "All portfolios have been fetched\n", ((MockModel) model).log);
  }

  /**
   * Test for Readable fail
   */
  @Test(expected = IllegalStateException.class)
  public void test15() {
    Investor model = new User();
    Readable readable = new StringReader("");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Tst for illegal State for appendable failing.
   */
  @Test(expected = IllegalStateException.class)
  public void test16() throws IOException {
    Investor model = new User();
    Readable readable = new StringReader("8\n");
    File file =
            new File("C:\\Users\\keviv\\Documents\\CS5010\\" +
                    "projects\\StockProject\\InvestingMVC\\new.txt");
    Appendable appendable = new FileWriter(file);
    ((FileWriter) appendable).close();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Test for date validation.
   */
  @Test
  public void test17() {
    Investor model = new User();
    Readable readable = new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-40\nn\n2018-13-10\nn\n" +
            "2018-11-13\n10\n\n2\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Incorrect Date Enter again:\n" +
            "Incorrect Date Enter again:\n" +
            "Incorrect Date Enter again:\n" +
            "Incorrect Date Enter again:\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "GOOG: $10360 Number of Stocks : 10\n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test for numerical input fail.
   */
  @Test
  public void test18() {
    Investor model = new User();
    Readable readable = new StringReader("1\nC\n4\nC\nGOOG\nn\n2018-11-13\na\n!\n" +
            "10\n\n2\nC\nn\n2018-11-13\n\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "Enter a valid integer\n" +
            "Enter a valid integer\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "GOOG: $10360 Number of Stocks : 10\n" +
            "\n" +
            "Press any button to continue.\n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test check tilda for string input escape to menu.
   */
  @Test
  public void test19() {
    Investor model = new User();
    Readable readable = new StringReader("1\n~\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test tilda for date escape to menu.
   */
  @Test
  public void test20() {
    Investor model = new User();
    Readable readable = new StringReader("1\nport\n2\nport\n~\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }

  /**
   * Test check company ticker tilda escape to menu.
   */
  @Test
  public void test21() {
    Investor model = new User();
    Readable readable = new StringReader("1\nport\n4\nport\n~\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test check tilda for date escape to menu.
   */
  @Test
  public void test22() {
    Investor model = new User();
    Readable readable = new StringReader("1\nport\n4\nport\nGOOG\n~\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n", appendable.toString());
  }

  /**
   * Test check tilda for numerical input escape to menu.
   */
  @Test
  public void test23() {
    Investor model = new User();
    Readable readable = new StringReader("1\nport\n4\nport\nGOOG\nn\n2018-11-13\n~\n8\n");
    Appendable appendable = new StringBuffer();
    InvestorView view = new InvestorView(appendable);
    InvestorControllerImpl i = new InvestorControllerImpl(readable);
    i.startApplication(model, view);
    assertEquals("\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n" +
            "\n" +
            "Enter name for the portfolio: \n" +
            "\n" +
            "Enter the company ticker: \n" +
            "\n" +
            "Do you want to use current date ? (Y for yes) \n" +
            "\n" +
            "Input Date(yyyy-mm-dd): \n" +
            "\n" +
            "Input the number of shares of the stock you wish to buy: \n" +
            "\n" +
            "\t\t\tMenu\n" +
            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n\n", appendable.toString());
  }
}
