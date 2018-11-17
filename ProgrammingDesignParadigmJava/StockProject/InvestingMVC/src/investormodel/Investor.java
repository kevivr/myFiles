package investormodel;

import java.util.Date;

/**
 * This is the interface of the Investor model. It is parameterized over the stock type, i.e. when
 * you implement it, you can substitute K with your implementation of a Stock.
 */
public interface Investor<K> {

  /**
   * Method to enable the investor to create a portfolio. A Portfolio is simply a collection of
   * stocks. The only restriction imposed on an investor for creating a portfolio is that he
   * shouldn't have two portfolio's name with the same name.
   *
   * @param portfolioName the String that is to be the portfolio's name.
   */
  void createPortFolio(String portfolioName);

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
  void buyStock(String portfolio, String stock, Integer count, Date date)
          throws IllegalArgumentException;

  /**
   * Method to return the total value of shares of a Portfolio. The restriction here is that the
   * Portfolio for which the user intends to fetch the total value has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time for which the user intends to buy shares of a particular stock.
   * @param portfolioName the portfolio for which value is to be returned.
   * @return String that represents each stock and its value.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid or Date is
   *                                  invalid.
   */
  String getPortfolioValueByStocks(String portfolioName, Date date) throws IllegalArgumentException;

  /**
   * Method to return the total cost basis of shares of a Portfolio. The restriction here is that
   * the Portfolio for which the user intends to fetch the cost basis has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time at and before which the user intends to check for cost basis of
   *                      his portfolio.
   * @param portfolioName the portfolio for which the cost basis is to be returned.
   * @return String that represents each stock and its value.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid.
   */
  String getPortfolioCostBasis(String portfolioName, Date date) throws IllegalArgumentException;

  /**
   * Method to return the total value of shares of a Portfolio. The restriction here is that the
   * Portfolio for which the user intends to fetch the cost basis has to be one of the investors
   * existing Portfolios.
   *
   * @param date          The time at which the user intends to check for value of his portfolio.
   * @param portfolioName the portfolio for which the cost basis is to be returned.
   * @return String that is the total value of the portfolio at given date.
   * @throws IllegalArgumentException if the user doesn't have a portfolio with the same name as the
   *                                  portfolio passed as an argument or date is invalid.
   */
  String getPortfolioTotalValue(String portfolioName, Date date) throws IllegalArgumentException;

  /**
   * Method to return a String that contains the cost basis of each stock till a particular date.
   *
   * @param portfolioName the name of the portfolio to be considered.
   * @param date          the date before which all stocks bought are to be considered.
   * @return the String that contains the cost basis of each stock till a particulat date.
   */
  String getPortfolioCostBasisByStocks(String portfolioName, Date date)
          throws IllegalArgumentException;

  /**
   * Method to return all Portfolios of a user.
   *
   * @return String that repreesnts all portfolio's of a user.
   */
  String getAllPortfolio();
}
