package investormodel;

import java.io.Serializable;
import java.util.Date;

/**
 * The class that represents a Stock, has fields that represent the ticker of the stock, price at
 * which the stock was bought, the day at which the stock was bought and the number of shares of the
 * stock bought. Package private class to ensure class is not used outside package.
 */
class Stock implements Serializable {
  private final String ticker;
  private final Double costPrice;
  private final Date boughtDayTime;
  private int numberOfShares;

  /**
   * Parameterized Constructor to construct a Stock Object.
   *
   * @param ticker         the String to initialize this Stock's ticker with.
   * @param costPrice      the Price at which the Stock was bought.
   * @param numberOfShares the number of shares of the stock bought.
   * @param boughtDayTime  the date at which the stock was bought.
   */
  public Stock(String ticker, Double costPrice, int numberOfShares, Date boughtDayTime) {
    this.ticker = ticker;
    this.costPrice = costPrice;
    this.numberOfShares = numberOfShares;
    this.boughtDayTime = boughtDayTime;
  }

  /**
   * Gets ticker.
   *
   * @return the ticker
   */
  String getTicker() {
    return ticker;
  }

  /**
   * Gets cost price.
   *
   * @return the cost price
   */
  Double getCostPrice() {
    return costPrice;
  }

  /**
   * Gets number of stocks.
   *
   * @return the number of stocks
   */
  int getNumberOfShares() {
    return numberOfShares;
  }

  /**
   * Gets bought day time.
   *
   * @return the bought day time
   */
  Date getBoughtDayTime() {
    return boughtDayTime;
  }
}
