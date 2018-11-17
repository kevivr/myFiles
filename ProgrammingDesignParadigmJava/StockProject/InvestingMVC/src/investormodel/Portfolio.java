package investormodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class Portfolio that represents a collection of stocks. Has methods to return cost basis and
 * value of a Portfolio and add stocks to a Portfolio. Package private class to ensure class is not
 * used outside package
 */
class Portfolio implements Serializable {
  private String name;
  private Map<String, List<Stock>> portfolio;
  private DataAccess dao;

  /**
   * Instantiates a new Portfolio Object. It takes in a String parameter, sets the name of the
   * portfolio to the parameter and also initializes the Map that represents different dated stocks
   * to an empty hashmap.
   *
   * @param s the String to set the name of this portfolio to.
   */
  Portfolio(String s) {
    this.name = s;
    this.portfolio = new HashMap<>();
    this.dao = new DataAccess();
  }

  /**
   * Method to return the name of this portfolio.
   *
   * @return the name of this portfolio.
   */
  String getName() {
    return this.name;
  }

  /**
   * Method to buy shares of some Stock for some date in this Portfolio.
   *
   * @param stock the Stock whose shares to buy.
   * @param count the number of shares to buy.
   * @param date  the date at which to buy.
   */
  void buyStock(String stock, Integer count, Date date) {
    if (portfolio.containsKey(stock)) {
      for (Stock s : portfolio.get(stock)) {
        if (date.compareTo(s.getBoughtDayTime()) == 0) {
          Stock newStock = new Stock(stock, s.getCostPrice(),
                  s.getNumberOfShares() + count, date);
          portfolio.get(stock).set(portfolio.get(stock).indexOf(s), newStock);
          return;
        }
      }
    }
    Map<Date, Double> stocksReturned = dao.getData(stock, date);
    if (!stocksReturned.containsKey(date)) {
      throw new IllegalStateException("Date entered is a holiday");
    }
    Double stockPrice = 0.0;
    for (Date d : stocksReturned.keySet()) {
      stockPrice += stocksReturned.get(d);
    }
    Stock newStock = new Stock(stock, stockPrice, count, date);
    if (portfolio.containsKey(stock)) {
      portfolio.get(stock).add(newStock);
    } else {
      List<Stock> tempList = new ArrayList<>();
      tempList.add(newStock);
      portfolio.put(stock, tempList);
    }
  }

  /**
   * Calculates the cost basis per company in this portfolio and returns them in a readable format.
   * which is in "Company Name": $ value.
   *
   * @param d1 the date at which the cost basis of each stock in this portfolio is to be returned.
   * @return the cost basis per company which exists in the portfolio.
   */
  String getCostBasisForEachStock(Date d1) {
    Date d2 = getNextDay(d1);
    String portfolioSummary = "";
    if (this.portfolio.isEmpty()) {
      return "There are no Stocks in this portfolio";
    }
    for (String s : this.portfolio.keySet()) {
      List<Stock> l = this.portfolio.get(s).stream().filter(e -> e.getBoughtDayTime()
              .before(d2)).collect(Collectors.toList());
      if (l.isEmpty()) {
        continue;
      }
      portfolioSummary =
              portfolioSummary + l.get(0).getTicker() + ": $"
                      + String.valueOf(companyValue(l)) + " Number of Stocks : "
                      + String.valueOf(companyStocksCount(l))
                      + '\n';
    }
    if (portfolioSummary.equals("")) {
      return "There are no Stocks in this portfolio";
    }
    return portfolioSummary;
  }

  /**
   * Calculates the value per Stock in this portfolio and returns them in a readable format. which
   * is in "Company Name": $ value
   *
   * @param d1 the date at which the valuation of each stock in this portfolio is to be returned.
   * @return the value per company which exists in the portfolio.
   */
  String evaluatePortfolio(Date d1) {
    Date d2 = getNextDay(d1);
    String portfolioSummary = "";
    if (this.portfolio.isEmpty()) {
      return "This portfolio is empty";
    }
    for (String s : this.portfolio.keySet()) {
      List<Stock> l = this.portfolio.get(s).stream().filter(e -> e.getBoughtDayTime()
              .before(d2)).collect(Collectors.toList());
      if (l.isEmpty()) {
        continue;
      }
      portfolioSummary =
              portfolioSummary + l.get(0).getTicker() + ": $"
                      + String.valueOf(companyStockValue(l, d1)) + " Number of Stocks : "
                      + String.valueOf(companyStocksCount(l))
                      + '\n';
    }
    if (portfolioSummary.equals("")) {
      return "This portfolio is empty";
    }
    return portfolioSummary;
  }

  /**
   * Calculates the cost basis for the entire portfolio. Returns as Portfolio cost basis: $ value.
   *
   * @param d1 the date at which to calculate the total cost basis of the Portfolio.
   * @return returns the cost basis of the portfolio as a readable string.
   */
  String portfolioTotalCostBasis(Date d1) {
    if (this.portfolio.isEmpty()) {
      return "This portfolio is empty";
    }
    Date d2 = getNextDay(d1);
    long value = 0;
    for (String s : this.portfolio.keySet()) {
      List<Stock> l = this.portfolio.get(s).stream().filter(e -> e.getBoughtDayTime()
              .before(d2)).collect(Collectors.toList());
      if (l.isEmpty()) {
        continue;
      }
      value += companyValue(l);
    }
    return "Portfolio cost basis: $" + String.valueOf(value);
  }

  /**
   * Calculates the value for the entire portfolio at the given date. Returns as Portfolio cost
   * basis: $ value.
   *
   * @param d1 the date at which to calculate the total value of the Portfolio.
   * @return returns the cost basis of the portfolio as a readable string.
   */
  String portfolioTotalValue(Date d1) {
    if (this.portfolio.isEmpty()) {
      return "This portfolio is empty";
    }
    Date d2 = getNextDay(d1);
    long value = 0;
    for (String s : this.portfolio.keySet()) {
      List<Stock> l = this.portfolio.get(s).stream().filter(e -> e.getBoughtDayTime()
              .before(d2)).collect(Collectors.toList());
      if (l.isEmpty()) {
        continue;
      }
      value += companyStockValue(l, d1);
    }
    return "Portfolio value: $" + String.valueOf(value);

  }

  /**
   * Calculates the cost basis of all the shares of a particular stock.
   *
   * @param l Different dated shares of a particular stock.
   * @return the value of the stock as a long value.
   */
  private long companyValue(List<Stock> l) {
    long value = 0;
    for (Stock aL : l) {
      value += aL.getNumberOfShares() * aL.getCostPrice();
    }
    return value;
  }

  /**
   * Calculates the total value of all the stocks purchased from a particular company at a given
   * date.
   *
   * @param l    Different dated shares of a particular stock.
   * @param date the date at which to calculate the value.
   * @return the value of the stock as a long value.
   */
  private long companyStockValue(List<Stock> l, Date date) {
    long value = 0;
    for (Stock aL : l) {
      Map<Date, Double> stocksReturned = dao.getData(aL.getTicker(), date);
      long stockPrice = 0;
      for (Date d : stocksReturned.keySet()) {
        stockPrice += stocksReturned.get(d);
      }
      value += aL.getNumberOfShares() * stockPrice;
    }
    return value;
  }

  /**
   * Returns the total count of all stocks for a particular company.
   *
   * @param l Different dated shares of a particular stock.
   */
  private int companyStocksCount(List<Stock> l) {
    int value = 0;
    for (Stock aL : l) {
      value += aL.getNumberOfShares();
    }
    return value;
  }


  /**
   * Helper method to return next day when provided with a Date Argument.
   *
   * @param date the date whose next date needs to be returned.
   * @return the next date given a date.
   */
  private Date getNextDay(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.DATE, 1);  // number of days to add
    return c.getTime();  // dt is now the new date
  }
}
