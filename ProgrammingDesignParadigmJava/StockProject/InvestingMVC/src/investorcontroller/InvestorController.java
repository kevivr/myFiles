package investorcontroller;

import investormodel.Investor;
import investorview.InvestorView;

/**
 * A controller interface with a start application function which serves as the focal point between
 * the view and the model.
 */
public interface InvestorController {
  /**
   * Method starts the investing training application using a model and view object.
   *
   * @param model a object of type user which serves as the connection from controller to model
   * @param view  a Object of InvestorView which serves as the connection from controller to view
   */
  void startApplication(Investor model, InvestorView view);
}
