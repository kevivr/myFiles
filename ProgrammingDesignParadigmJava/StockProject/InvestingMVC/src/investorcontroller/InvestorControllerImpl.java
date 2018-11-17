package investorcontroller;

import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import investormodel.Investor;
import investormodel.User;
import investorview.InvestorView;

/**
 * An implementation of the investor controller. Class has readable and scanner objects as instance
 * variables to allow various ways of taking inputs and giving outputs. (Eg. through files, through
 * Strings, through console etc). Implements the investorController interface and all its methods.
 */
public class InvestorControllerImpl implements InvestorController {
  private Scanner scan;

  /**
   * Parameterized constructor to initialize the readable and scanner object.
   *
   * @param in the Readable object to set to.
   */
  public InvestorControllerImpl(Readable in) {
    Readable input;
    if (in == null) {
      throw new IllegalArgumentException("readable cannot be null");
    }
    input = in;
    this.scan = new Scanner(input);
  }

  /**
   * The method that is called to Start the Investor Application.
   */
  public static void main(String[] args) {
    Investor model = new User();
    InvestorView view = new InvestorView(System.out);
    InvestorControllerImpl i = new InvestorControllerImpl(new InputStreamReader(System.in));
    i.startApplication(model, view);
  }

  /**
   * Method starts the investing training application using a model and view object and does approp
   * riate Operations depending on the user's input.
   *
   * @param model a object of type user which serves as the connection from controller to model.
   * @param view  a Object of InvestorView which serves as the connection from controller to view.
   * @throws IllegalArgumentException when Model or View is null.
   */
  @Override
  public void startApplication(Investor model, InvestorView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or View cannot be null");
    }
    while (true) {
      try {
        view.displayMenu();
        String investorInput = scan.nextLine();
        switch (investorInput) {
          case "1":
            view.enterPortfolioPrompt();
            createPortfolio(model);
            break;
          case "2":
            String portfolioName = enterPortfolio(view);
            if (portfolioName.equals("")) {
              continue;
            }
            Date date1 = enterDate(view);
            if (date1 == null) {
              continue;
            }
            view.viewPortfolio(model.getPortfolioCostBasisByStocks(portfolioName, date1));
            enterContinue(view);
            break;
          case "3":
            portfolioName = enterPortfolio(view);
            if (portfolioName.equals("")) {
              continue;
            }
            date1 = enterDate(view);
            view.viewPortfolio(model.getPortfolioValueByStocks(portfolioName, date1));
            enterContinue(view);
            break;
          case "4":
            portfolioName = enterPortfolio(view);
            if (portfolioName.equals("")) {
              continue;
            }
            String companyTicker = enterCompany(view);
            if (companyTicker.equals("")) {
              continue;
            }
            date1 = enterDate(view);
            if (date1 == null) {
              continue;
            }
            int stock = enterInt(view);
            if (stock == -1) {
              continue;
            }
            model.buyStock(portfolioName, companyTicker, stock, date1);
            enterContinue(view);
            break;
          case "5":
            portfolioName = enterPortfolio(view);
            if (portfolioName.equals("")) {
              continue;
            }
            date1 = enterDate(view);
            if (date1 == null) {
              continue;
            }
            view.viewPortfolio(model.getPortfolioCostBasis(portfolioName, date1));
            enterContinue(view);
            break;
          case "6":
            view.viewPortfolio(model.getAllPortfolio());
            enterContinue(view);
            break;
          case "7":
            portfolioName = enterPortfolio(view);
            if (portfolioName.equals("")) {
              continue;
            }
            date1 = enterDate(view);
            if (date1 == null) {
              continue;
            }
            view.viewPortfolio(model.getPortfolioTotalValue(portfolioName, date1));
            enterContinue(view);
            break;
          case "8":
            return;
          default:
            view.viewPortfolio("Invalid Input please try again");
            break;
        }
      } catch (IllegalArgumentException e) {
        view.viewPortfolio(e.getMessage());
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Input Fail");
      }
    }
  }

  /**
   * Private helper method to call Model's create Portfolio method to create a portfolio for the
   * user when he/she gives valid inputs.
   *
   * @param model the model whose createPortfolio method is to be called.
   */
  private void createPortfolio(Investor model) {
    String portfolioName = scan.nextLine();
    if (checkInputForTilda(portfolioName)) {
      return;
    }
    model.createPortFolio(portfolioName);
  }

  /**
   * Checks and returns the Date object if it is valid else prompts the view to ask the user to
   * input a valid date.
   *
   * @param view the View of the application.
   * @return Date if valid.
   */
  private Date getDate(InvestorView view) {
    while (true) {
      String date = scan.nextLine();
      if (checkInputForTilda(date)) {
        return null;
      }
      Calendar cal = Calendar.getInstance();
      cal.setLenient(false);
      try {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date d1 = sdf.parse(date);
        return d1;
      } catch (Exception e) {
        view.viewPortfolio("Incorrect Date Enter again:");
      }
    }
  }

  /**
   * Private helper method to ask the user to input Portfolios name.
   *
   * @param view the View of the application.
   * @return String entered if it is valid.
   */
  private String enterPortfolio(InvestorView view) {
    view.enterPortfolioPrompt();
    String portfolioName = scan.nextLine();
    if (checkInputForTilda(portfolioName)) {
      return "";
    }
    return portfolioName;
  }

  /**
   * If user enters "y", then takes's today's date into  consideration else prompts User for date
   * input and checks if it is valid and returns it.
   *
   * @param view the view of the application.
   * @return the Date if valid, else null.
   */
  private Date enterDate(InvestorView view) {
    view.datePrompt();
    String datePrompt = scan.nextLine();
    if (checkInputForTilda(datePrompt)) {
      return null;
    }
    if (datePrompt.equalsIgnoreCase("y")) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      dateFormat.format(date);
      String s = dateFormat.format(date);
      try {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        return d;
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    view.enterDate();
    return getDate(view);
  }

  /**
   * Waits for newline so menu can be displayed again.
   *
   * @param view the View of the application.
   */
  private void enterContinue(InvestorView view) {
    view.enterToContinue();
    scan.nextLine();
  }

  /**
   * Checks if Stirng input is valid and returns the Company ticker to be passed.
   *
   * @param view the View of the application.
   * @return the String for Ticker if valid.
   */
  private String enterCompany(InvestorView view) {
    view.enterCompanyPrompt();
    String companyTicker = scan.nextLine();
    if (checkInputForTilda(companyTicker)) {
      return "";
    }
    return companyTicker;
  }

  /**
   * If integer input is valid, returns that else -1.
   *
   * @param view the View of the application.
   * @return Integer if valid, else -1.
   */
  private int enterInt(InvestorView view) {
    view.noOfStock();
    String temp = scan.nextLine();
    if (checkInputForTilda(temp)) {
      return -1;
    }
    while (true) {
      try {
        int stock = Integer.parseInt(temp);
        if (stock < 0) {
          throw new NumberFormatException("No negative numbers");
        }
        return stock;
      } catch (NumberFormatException e) {
        view.viewPortfolio("Enter a valid integer");
        temp = scan.nextLine();
      }
    }
  }

  /**
   * Returns if the input is ~, so the User can be redirected to the main menu.
   *
   * @param s the String to check against.
   * @return true if String is ~ else false.
   */
  private boolean checkInputForTilda(String s) {
    return s.contains("~");
  }
}
