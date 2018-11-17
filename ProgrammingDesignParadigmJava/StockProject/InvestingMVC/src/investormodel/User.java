package investormodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents a simple investor. It implements the investor interface and overrides all
 * of it methods. Through an object of this class, an investor can create a portfolio, buy stocks
 * and add them to a portfolio, and also has methods that enable the user to examine his/her
 * portfolios.
 */
public class User implements Investor<Stock>, Serializable {
  private Collection<Portfolio> myPortfolios;

  /**
   * Constructor to initialize new User objects.
   */
  public User() {
    this.myPortfolios = new ArrayList<>();
  }

  /**
   * Method to enable the investor to create a portfolio. A Portfolio is simply a collection of
   * stocks. The only restriction imposed on an investor for creating a portfolio is that he
   * shouldn't have two portfolio's name with the same name.
   *
   * @param portfolioName the String that is to be the portfolio's name.
   */
  @Override
  public void createPortFolio(String portfolioName) {
    if (portfolioName == null || portfolioName.equals("")) {
      throw new IllegalArgumentException("Invalid portfolioName");
    }
    portfolioName = portfolioName.trim();
    if (!portfolioName.matches("^[a-zA-Z0-9\\s]+$")) {
      throw new IllegalArgumentException("Name cannot contain special " +
              "characters or start with space");
    }
    List<String> l = myPortfolios.stream().map(e -> e.getName()).collect(Collectors.toList());
    if (!l.contains(portfolioName)) {
      myPortfolios.add(new Portfolio(portfolioName));
    } else {
      throw new IllegalArgumentException("Portfolio already exists");
    }
  }

  /**
   * Method to enable the investor to buy some shares of a particular stock for a portfolio at a
   * given date.
   *
   * @param portfolio The portfolio to which the user wants to add the stock he is buying.
   * @param stock     The String representation of the stock the user intends to buy.
   * @param count     The integral count of the number of shares of the stock the user intends to
   *                  buy.
   * @param date      The time for which the user intends to buy shares of a particular stock.
   * @throws IllegalArgumentException when portfolio is not present or stock is unavailable ot
   *                                  invalid values are inputed for count and date.
   */
  @Override
  public void buyStock(String portfolio, String stock, Integer count, Date date)
          throws IllegalArgumentException {
    if (count <= 0) {
      throw new IllegalArgumentException("Invalid value for count argument");
    }
    checkDate(date);
    try {
      Portfolio requiredPortfolio = portfolioChecker(portfolio);
      requiredPortfolio.buyStock(stock, count, date);
    } catch (IllegalArgumentException e) {
      this.createPortFolio(portfolio);
      this.buyStock(portfolio, stock, count, date);
    } catch (IllegalStateException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Method to return a String that contains the cost basis of each stock till a particular date.
   *
   * @param portfolioName the name of the portfolio to be considered.
   * @param date          the date before which all stocks bought are to be considered.
   * @return the String that contains the cost basis of each stock till a particulat date.
   */
  @Override
  public String getPortfolioCostBasisByStocks(String portfolioName, Date date)
          throws IllegalArgumentException {
    checkDate(date);
    Portfolio requiredPortfolio = portfolioChecker(portfolioName);
    if (requiredPortfolio != null) {
      return requiredPortfolio.getCostBasisForEachStock(date);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
  }

  /**
   * Method to return all Portfolios of a user.
   *
   * @return String that repreesnts all portfolio's of a user.
   */
  @Override
  public String getAllPortfolio() {
    List<String> l = this.myPortfolios.stream().map(e -> e.getName()).collect(Collectors.toList());
    if (l.isEmpty()) {
      return "No Portfolios yet";
    }
    return l.toString();
  }

  /**
   * Method to return the total value of shares of a Portfolio. The restriction here is that the
   * Portfolio for which the user intends to fetch the total value has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time for which the user intends to buy shares of a particular stock.
   * @param portfolioName the portfolio for which value is to be returned.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid or Date is
   *                                  invalid.
   */
  @Override
  public String getPortfolioValueByStocks(String portfolioName, Date date)
          throws IllegalArgumentException {
    checkDate(date);
    Portfolio requiredPortfolio = portfolioChecker(portfolioName);
    if (requiredPortfolio != null) {
      return requiredPortfolio.evaluatePortfolio(date);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
  }

  /**
   * Method to return the total cost basis of shares of a Portfolio. The restriction here is that
   * the Portfolio for which the user intends to fetch the cost basis has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time at and before which the user intends to check for cost basis of
   *                      his portfolio.
   * @param portfolioName the portfolio for which the cost basis is to be returned.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid.
   */
  @Override
  public String getPortfolioCostBasis(String portfolioName, Date date)
          throws IllegalArgumentException {
    checkDate(date);
    Portfolio requiredPortfolio = portfolioChecker(portfolioName);
    if (requiredPortfolio != null) {
      return requiredPortfolio.portfolioTotalCostBasis(date);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
  }

  /**
   * Method to return the total value of shares of a Portfolio. The restriction here is that the
   * Portfolio for which the user intends to fetch the cost basis has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time at which the user intends to check for value of his portfolio.
   * @param portfolioName the portfolio for which the cost basis is to be returned.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid.
   */
  @Override
  public String getPortfolioTotalValue(String portfolioName, Date date)
          throws IllegalArgumentException {
    checkDate(date);
    Portfolio requiredPortfolio = portfolioChecker(portfolioName);
    if (requiredPortfolio != null) {
      return requiredPortfolio.portfolioTotalValue(date);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
  }

  /**
   * Helper method to return the portfolio with name that matches String argument passed if it
   * exists.
   *
   * @param portfolioName the String argument to be checked against.
   * @return the Portfolio object with the name equal to the String argument passed.
   */
  private Portfolio portfolioChecker(String portfolioName) {
    List<String> l = myPortfolios.stream().map(e -> e.getName()).collect(Collectors.toList());
    if (l.contains(portfolioName)) {
      List<Portfolio> p = myPortfolios.stream()
              .filter(portfolio -> portfolio.getName()
                      .equals(portfolioName))
              .collect(Collectors.toList());
      return p.get(0);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
  }

  /**
   * Helper method to check if date passed is not in the future.
   *
   * @param date the date to perform check against.
   */
  private void checkDate(Date date) {
    Date today = Calendar.getInstance().getTime();
    if (today.compareTo(date) < 0) {
      throw new IllegalArgumentException("Date passed cannot be greater than today");
    }
  }

}
