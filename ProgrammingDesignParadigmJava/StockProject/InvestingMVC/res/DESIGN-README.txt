The MVC design of our Simple stock buying simulator has 

 
investormodel package: 
investorview
Controller package: 
investorcontroller

Model Package:

investormodel :




Investor interface: This is the interface of the Investor model. It is parameterized over the stock type, i.e. when you implement it, 
you can substitute K with your implementation of a Stock.



User class implements Investor: Class that represents a simple investor. It implements the investor interface and overrides all of its 
methods. Through an object of this class, an investor can create a portfolio, buy stocks and add them to a portfolio, and also has 
methods that enable the user to examine his/her portfolios.



Portfolio class: Class Portfolio that represents a collection of stocks. Has methods to return cost basis and value of a Portfolio and 
add stocks to a Portfolio.



Stock class: The class that represents a Stock, has fields that represent the ticker of the stock, price at which the stock was bought, 
the day at which the stock was bought and the number of shares of the stock bought.



DataAccess class: Class whose objects accomplish appropriate data retrieval.






investorcontroller: 




InvestorController interface: A controller interface with a start application function which serves as the focal point between the view 
and the model.

InvestorControllerImpl class: An implementation of the investor controller. Class has readable and Scanner objects that allow various 
methods of taking inputs. It implements the InvestorController interface and overrides all of its methods. Its method appropriately 
named call model method as appropriate and call view methods to display to the user appropriate directions on how to use the Application 
or menu.


investorview:


InvestorView class: An implementation of the Investor simulation application view. Class has appendable object as instance variable to 
allow various ways of giving outputs. (Eg. to files, to Strings, to console etc).
