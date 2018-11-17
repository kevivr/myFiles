package investormodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class whose objects accomplish appropriate data retrieval. Package private to ensure class is not
 * used outside package.
 */
class DataAccess implements Serializable {

  private static Map<String, List<APIData>> dataCache;

  /*
    Static block to instantiate static date cache.
   */
  static {
    dataCache = new HashMap<>();
  }

  private String apiKey;
  private URL url;

  /**
   * Instantiates a new DataAccess Object.
   */
  DataAccess() {
    apiKey = "W0M1JOKC82EZEQA8";
    url = null;
  }

  /**
   * Given a stock ticker and date, return the value of the stock at the date entered.
   *
   * @param stockSymbol the ticker for which the value is to be returned.
   * @param date        the date for which the value is to be returned.
   * @return the value of the stock at the date entered.
   */
  private Map<Date, Double> getStockValue(String stockSymbol, Date date) {
    List<APIData> stockData = dataCache.get(stockSymbol);
    int lowIndex = 0;
    int lastIndex = stockData.size() - 1;
    int middleIndex = 0;
    while (lowIndex < lastIndex) {
      middleIndex = (lowIndex + (lastIndex - 1)) / 2;
      Date middleDate = stockData.get(middleIndex).getTimestamp();
      Date lowDate = stockData.get(lowIndex).getTimestamp();
      Date highDate = stockData.get(lastIndex).getTimestamp();
      if (lowDate.compareTo(date) == 0) {
        HashMap<Date, Double> toReturn = new HashMap<>();
        toReturn.put(date, stockData.get(lowIndex).getPrice());
        return toReturn;
      }
      if (highDate.compareTo(date) == 0) {
        HashMap<Date, Double> toReturn = new HashMap<>();
        toReturn.put(date, stockData.get(lastIndex).getPrice());
        return toReturn;
      }
      if (middleDate.compareTo(date) == 0) {
        HashMap<Date, Double> toReturn = new HashMap<>();
        toReturn.put(date, stockData.get(middleIndex).getPrice());
        return toReturn;
      } else if (middleDate.compareTo(date) < 0) {
        lastIndex = middleIndex - 1;
      } else {
        lowIndex = middleIndex + 1;
      }
    }
    if (stockData.get(middleIndex).getTimestamp().compareTo(date) > 0) {
      HashMap<Date, Double> toReturn = new HashMap<>();
      toReturn.put(stockData.get(middleIndex + 1).getTimestamp(), stockData.get(middleIndex + 1)
              .getPrice());
      return toReturn;
    } else {
      HashMap<Date, Double> toReturn = new HashMap<>();
      toReturn.put(stockData.get(middleIndex).getTimestamp(), stockData.get(middleIndex)
              .getPrice());
      return toReturn;
    }
  }

  /**
   * Gets the closing price of the date entered for the stock entered. If data is already present in
   * the cache, it doesn't hit the API, else it hits the API and fetches relevant data and adds it
   * to the cache before returning price.
   *
   * @param stockSymbol the stock symbol whose price is to be retrieved.
   * @param date        the date for which the price is to be retrieved.
   * @return the closing price of the data entered for the stock entered.
   */
  Map<Date, Double> getData(String stockSymbol, Date date) {
    stockSymbol = stockSymbol.toUpperCase();
    if (!dataCache.containsKey(stockSymbol)) {
      getFromAPI(stockSymbol);
    }
    return getStockValue(stockSymbol, date);
  }

  /**
   * A Private helper method to construct the URL for the API to fetch data from.
   *
   * @param stockSymbol the stock to be appended to the API url.
   */
  private void constructURL(String stockSymbol) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
  }

  /**
   * If data to be fetched is not present in the cache, method used to hit the API and return
   * appropriate data.
   *
   * @param stockSymbol the ticker of the stock whose data is to be returned from the API.
   */
  private void getFromAPI(String stockSymbol) {
    stockSymbol = stockSymbol.toUpperCase();
    constructURL(stockSymbol);

    InputStream input;
    StringBuilder out = new StringBuilder();
    try {
      input = url.openStream();
      int b;
      while ((b = input.read()) != -1) {
        out.append((char) b);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (out.toString().contains("Error Message")) {
      throw new IllegalArgumentException("Ticker Doesn't exist");
    }
    dataCache.put(stockSymbol, new ArrayList<>());
    String[] newLineSplit = out.toString().split("\n");
    for (int i = 1; i < newLineSplit.length; i++) {
      String[] commaSplit = newLineSplit[i].split(",");
      try {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(commaSplit[0]);
        Double price1 = Double.parseDouble(commaSplit[4]);
        dataCache.get(stockSymbol).add(new APIData(date1, price1));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * A Private Class to help build the hashTable that caches data fetched from API.
   */
  private static class APIData {
    private Date timestamp;
    private Double price;

    /**
     * Instantiates a new APIData Object.
     *
     * @param timestamp the timestamp for the new APIData Object.
     * @param price     the price for the new APIData Object.
     */
    private APIData(Date timestamp, Double price) {
      this.timestamp = timestamp;
      this.price = price;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    private Date getTimestamp() {
      return timestamp;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    private Double getPrice() {
      return price;
    }
  }
}
