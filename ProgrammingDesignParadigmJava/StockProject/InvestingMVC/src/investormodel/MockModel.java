package investormodel;

import java.util.Date;

public class MockModel implements Investor<Stock> {
  public String log;

  public MockModel() {
    log = "";
  }

  @Override
  public void createPortFolio(String portfolioName) {
    log += "Portfolio of name " + portfolioName + " created\n";
  }

  @Override
  public void buyStock(String portfolio, String stock, Integer count, Date date)
          throws IllegalArgumentException {
    log += "Stock for company " + stock + " bought for portfolio " + portfolio + " on "
            + date.toString() + " number of stocks " + count + "\n";
  }

  @Override
  public String getPortfolioValueByStocks(String portfolioName, Date date)
          throws IllegalArgumentException {
    log += "Stocks of the portfolio " + portfolioName + " viewed for value on Date: "
            + date.toString() + "\n";
    return null;
  }

  @Override
  public String getPortfolioCostBasis(String portfolioName, Date date)
          throws IllegalArgumentException {
    log += "Cost Basis for " + portfolioName + " on date: " + date.toString() + "\n";
    return null;
  }

  @Override
  public String getPortfolioTotalValue(String portfolioName, Date date)
          throws IllegalArgumentException {
    log += "Value for " + portfolioName + " on date: " + date.toString() + "\n";
    return null;
  }

  @Override
  public String getPortfolioCostBasisByStocks(String portfolioName, Date date)
          throws IllegalArgumentException {
    log += "Cost Basis per stock for " + portfolioName + " on date: " + date.toString() + "\n";
    return null;
  }

  @Override
  public String getAllPortfolio() {
    log += "All portfolios have been fetched\n";
    return null;
  }
}
