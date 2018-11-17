package investorview;

import java.io.IOException;

/**
 * An implementation of the Investor simulation application view. Class has appendable object as
 * instance variable to allow various ways of giving outputs. (Eg. to files, to Strings, to console
 * etc).
 */
public class InvestorView {

  private final Appendable out;

  /**
   * Parameterized constructor for the view that takes in Appendable object and sets the view's
   * Appendable member to it.
   *
   * @param out the Appendable object to set to.
   */
  public InvestorView(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Appendable is null");
    }
    this.out = out;
  }

  /**
   * Method to Append the menu to the Appendable.
   */
  public void displayMenu() {
    appendToView("\t\t\tMenu\n" +

            "1. Create new portfolio\n" +
            "2. View portfolio Cost Basis\n" +
            "3. View portfolio Value\n" +
            "4. Buy shares of a Stock\n" +
            "5. View total investment in portfolio\n" +
            "6. View all portfolio\n" +
            "7. View total value of portfolio\n" +
            "8. Exit\n" +
            "Enter Menu Option:\n");
  }

  /**
   * Method to Prompt user to enter a String for Portfolio name.
   */
  public void enterPortfolioPrompt() {
    appendToView("Enter name for the portfolio: \n");
  }

  /**
   * Method that takes in a String and appends it to the appendable.
   *
   * @param s The String that is to be appended.
   */
  public void viewPortfolio(String s) {
    try {
      out.append(s + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed\n");
    }
  }

  /**
   * Prompts user to enter date and the format to enter it in.
   */
  public void enterDate() {
    appendToView("Input Date(yyyy-mm-dd): \n");
  }

  /**
   * Prompts user to enter y if he/she wants to use today's date else will have to enter date.
   */
  public void datePrompt() {
    appendToView("Do you want to use current date ? (Y for yes) \n");
  }

  /**
   * Prompts user to enter the number of shares of the Stock to be bought.
   */
  public void noOfStock() {
    appendToView("Input the number of shares of the stock you wish to buy: \n");
  }

  /**
   * Method to append given string to the appendable.
   *
   * @param s String to append.
   */
  private void appendToView(String s) {
    try {
      out.append(s + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed\n");
    }
  }

  /**
   * Method that prompts user to enter Ticker of the stock that is to be bought.
   */
  public void enterCompanyPrompt() {
    appendToView("Enter the company ticker: \n");
  }

  /**
   * Method that takes in a String and newline to continue execution.
   */
  public void enterToContinue() {
    appendToView("Press any button to continue.\n");
  }
}
