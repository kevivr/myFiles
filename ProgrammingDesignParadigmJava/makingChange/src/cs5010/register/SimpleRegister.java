package cs5010.register;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * A SimpleRegister that implements the CashRegister interface. Has methods to add pennies, add
 * nickels, add dimes, add quarters and dollars. Also has a withdraw method that given an amount,
 * returns a map of denominations and its quantities that make up the amount if achievable using
 * total currency in the register.
 */
public class SimpleRegister implements CashRegister {
  /**
   * Members of the class. <br>
   * <ul>
   * <li> registerMap : Map of an integer key to an integer value that stores the different
   * denominations and its quantity.</li>
   * <li> total : The total value in register till now.</li>
   * <li> auditLog : The String that represents the log of all transactions till now.</li>
   * </ul>
   */
  private Map<Integer, Integer> registerMap = new TreeMap<>(Collections.reverseOrder());
  private Integer total = 0;
  private String auditLog = "";

  /**
   * Method to add currency to the register. Takes in two Integers, one that denotes the
   * denomination of currency and other that denotes the quantity of that denomination to be added.
   *
   * @param key Integer that denotes denomination of currency to be added to the registerMap.
   * @param num Integer that denotes the quantity of denomination to be added to the registerMap.
   * @throws IllegalArgumentException when the quantity of denomination passed is less than 0.
   */
  private void add(int key, int num) throws IllegalArgumentException {
    if (num < 0) {
      throw new IllegalArgumentException("Not valid amount of coins that can be added");
    }
    if (!this.registerMap.containsKey(key)) {
      this.registerMap.put(key, num);
    } else {
      this.registerMap.put(key, this.registerMap.get(key) + num);
    }
    this.total = this.total + key * num;
    double depositValue = ((double) key * (double) num / 100.00);
    this.auditLog = this.auditLog.concat(String.format("Deposit: %.2f\n", depositValue));
  }

  /**
   * Add pennies to the register.
   *
   * @param num number of pennies to be added.
   */
  @Override
  public void addPennies(int num) {
    add(1, num);
  }

  /**
   * Add nickels to the register.
   *
   * @param num number of nickels to be added
   */
  @Override
  public void addNickels(int num) {
    add(5, num);
  }

  /**
   * Add dimes to the register.
   *
   * @param num number of dimes to be added
   */
  @Override
  public void addDimes(int num) {
    add(10, num);
  }

  /**
   * Add quarters to the register.
   *
   * @param num number of quarters to be added
   */
  @Override
  public void addQuarters(int num) {
    add(25, num);
  }

  /**
   * Add one-dollar bills to the register.
   *
   * @param num number of ones to be added
   */
  @Override
  public void addOnes(int num) {
    add(100, num);
  }

  /**
   * Add five-dollar bills to the register.
   *
   * @param num number of fives to be added
   */
  @Override
  public void addFives(int num) {
    add(500, num);
  }

  /**
   * Add ten-dollar bills to the register.
   *
   * @param num number of tens to be added
   */
  @Override
  public void addTens(int num) {
    add(1000, num);
  }

  /**
   * Withdraw the provided amount from the cash register, if there is enough. The input is provided
   * in dollars and cents, instead of a floating-point number to avoid precision errors, as only two
   * decimal places of precision are realistic.
   *
   * <p>It works as follows:
   * 1. Start from highest denomination. 2. Find the highest number of coins/notes of current
   * denomination such that their value is less than required value. 3. Take that many notes/coins
   * out of the cash register, and reduce the required value by the appropriate amount. 4. If the
   * required value is greater than 0 and there is a lesser denomination, go to step 2. 5. If there
   * are no more denominations, throw an exception because the amount cannot be dispensed with what
   * the register contains.
   *
   * @param dollars the dollar amount to be withdrawn
   * @param cents   the cent amount to be withdrawn
   * @return if dispensing is possible, a map of &lt;value of coin/bill in cents, number of
   *         coins/bills&gt; that represents the change
   * @throws InsufficientCashException when there isn't enough currency in the cash register to
   *                                   withdraw the provided amount and when the amount passed is
   *                                   less than 0.
   */
  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    Integer amount = dollars * 100 + cents;
    if (amount > this.total || dollars < 0 || cents < 0) {
      throw new InsufficientCashException("Insufficient change, cannot withdraw");
    }
    Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
    Map<Integer, Integer> tempRegister = new TreeMap<>(Collections.reverseOrder());
    tempRegister.putAll(registerMap);
    for (Integer i : tempRegister.keySet()) {
      if (amount / i >= 1) {
        Integer temp = amount / i;
        if (tempRegister.get(i) >= temp) {
          amount = amount % i;
          result.put(i, temp);
          tempRegister.put(i, tempRegister.get(i) - temp);
        } else if (tempRegister.get(i) < temp) {
          amount = amount - i * tempRegister.get(i);
          result.put(i, tempRegister.get(i));
          tempRegister.put(i, 0);
        }
      }
    }
    if (amount != 0) {
      throw new InsufficientCashException("Insufficient change, cannot withdraw");
    }
    this.registerMap = tempRegister;
    this.auditLog = this.auditLog.concat(
            String.format("Withdraw: %.2f%n",
                    ((double) dollars * 100.00 + (double) cents) / 100.00));
    return result;
  }

  /**
   * Return the contents of this register as a map of &lt; value of coin/bill in cents,number of
   * coins/bills &gt;.
   *
   * @return the contents of this register as a map
   */
  @Override
  public Map<Integer, Integer> getContents() {
    return new TreeMap<Integer, Integer>(this.registerMap);
  }

  /**
   * Returns a string describing the history of transactions performed on the cash register. The
   * audit log is a series of transactions (1 per line). Each line is of the form: "Deposit: $x.y"
   * or "Withdraw: $x.y", where x is the dollar amount and y is cents up to 2 decimal places.
   *
   * @return the string of the audit log
   */
  @Override
  public String getAuditLog() {
    return this.auditLog.trim();
  }

}
