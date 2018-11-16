import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import cs5010.register.CashRegister;
import cs5010.register.InsufficientCashException;
import cs5010.register.SimpleRegister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class to test Functionality of SimpleRegister class that implements a cash register.
 */
public class SimpleRegisterTest {
  CashRegister sample = new SimpleRegister();
  Map<Integer, Integer> sampleRegMap = new TreeMap<>();

  /**
   * setup of variables used in more than one test method.
   */
  @Before
  public void setUp() {
    sample.addTens(30);
    sampleRegMap = sample.getContents();
  }

  /**
   * Test deposit and withdrawal of currency from a Simple Register. First deposit multiple times
   * and then check auuditLog after each insertion to ensure deposit of currency. Also check if
   * total before deposit is lesser than after deposit. Then, try to withdraw some amount of money
   * from the register. If withdraw was successful, test if the map returned by the withdraw method
   * contains the correct denominations and quantities. Also check if total before withdrawal is
   * greater than after withdrawal in the register.
   */
  @Test
  public void testDepositAndWithdraw() {
    CashRegister reg1 = new SimpleRegister();
    Map<Integer, Integer> reg2 = new TreeMap<>();

    reg1.addPennies(10);
    assertEquals("Deposit: 0.10", reg1.getAuditLog());

    reg1.addDimes(10);
    assertEquals("Deposit: 0.10\nDeposit: 1.00", reg1.getAuditLog());

    reg1.addNickels(10);

    Integer totalBeforeDeposit = 0;
    Map<Integer, Integer> regMap = reg1.getContents();
    for (Integer k : regMap.keySet()) {
      totalBeforeDeposit = totalBeforeDeposit + k * regMap.get(k);
    }

    assertEquals("Deposit: 0.10\nDeposit: 1.00\nDeposit: 0.50", reg1.getAuditLog());

    reg1.addOnes(1);

    Integer totalAfterDeposit = 0;
    Map<Integer, Integer> regMap1 = reg1.getContents();
    for (Integer k : regMap1.keySet()) {
      totalAfterDeposit = totalAfterDeposit + k * regMap1.get(k);
    }

    assertEquals(true, totalBeforeDeposit < totalAfterDeposit);

    assertEquals("Deposit: 0.10\nDeposit: 1.00\n" +
            "Deposit: 0.50\nDeposit: 1.00", reg1.getAuditLog());

    reg1.addFives(20);
    assertEquals("Deposit: 0.10\nDeposit: 1.00\n" +
            "Deposit: 0.50\nDeposit: 1.00\nDeposit: 100.00", reg1.getAuditLog());

    Integer totalBeforeWithdraw = 0;
    Map<Integer, Integer> regMap2 = reg1.getContents();
    for (Integer k : regMap2.keySet()) {
      totalBeforeWithdraw = totalBeforeWithdraw + k * regMap2.get(k);
    }

    try {
      reg2 = reg1.withdraw(0, 43);

      Map<Integer, Integer> temp = new TreeMap<>();
      temp.put(10, 4);
      temp.put(1, 3);

      assertEquals(true, temp.keySet().equals(reg2.keySet()));

      for (Integer i : reg2.keySet()) {
        assertEquals(reg2.get(i), temp.get(i));
      }

      Integer withdrawnTotal = 0;
      for (Integer k : reg2.keySet()) {
        withdrawnTotal = withdrawnTotal + k * reg2.get(k);
      }

      Integer totalAfterWithdraw = 0;
      Map<Integer, Integer> regMap3 = reg1.getContents();

      for (Integer k : regMap3.keySet()) {
        totalAfterWithdraw = totalAfterWithdraw + k * regMap3.get(k);
      }

      assertEquals(true, totalAfterWithdraw < totalBeforeWithdraw);
      assertEquals(java.util.Optional.ofNullable(withdrawnTotal),
              java.util.Optional.ofNullable(totalBeforeWithdraw - totalAfterWithdraw));

      assertEquals("Deposit: 0.10\n" +
              "Deposit: 1.00\n" +
              "Deposit: 0.50\n" +
              "Deposit: 1.00\n" +
              "Deposit: 100.00\n" +
              "Withdraw: 0.43", reg1.getAuditLog());
    } catch (InsufficientCashException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Test deposit of negative amount of pennies will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addPennies(-2);
  }

  /**
   * Test deposit of negative amount of nickels will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail2() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addNickels(-2);
  }

  /**
   * Test deposit of negative amount of dimes will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail3() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addDimes(-2);
  }

  /**
   * Test deposit of negative amount of quarters will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail4() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addQuarters(-2);
  }

  /**
   * Test deposit of negative amount of single dollar bills will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail5() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addOnes(-2);
  }

  /**
   * Test deposit of negative amount of five dollar bills will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail6() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addFives(-2);
  }

  /**
   * Test deposit of negative amount of ten dollar bills will fail.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDepositFail7() {
    CashRegister reg1 = new SimpleRegister();
    reg1.addTens(-2);
  }

  /**
   * Test withdrawal of total not presents in register will throw an exception.
   */
  @Test(expected = InsufficientCashException.class)
  public void testWithdrawFail() throws InsufficientCashException {
    CashRegister reg1 = new SimpleRegister();
    reg1.withdraw(1, 1);
  }

  /**
   * Test withdrawal of negative total will throw an exception.
   */
  @Test(expected = InsufficientCashException.class)
  public void testWithdrawFail2() throws InsufficientCashException {
    CashRegister reg1 = new SimpleRegister();
    reg1.addTens(2);
    reg1.withdraw(-1, 1);
  }

  /**
   * Test withdrawal of negative number of cents will throw an exception.
   */
  @Test(expected = InsufficientCashException.class)
  public void testWithdrawFail3() throws InsufficientCashException {
    CashRegister reg1 = new SimpleRegister();
    reg1.addTens(2);
    reg1.withdraw(1, -1);
  }

  /**
   * Test withdrawal throws an exception when there is enough amount but not correct denominations
   * to make the withdrawal.
   */
  @Test(expected = InsufficientCashException.class)
  public void testWithdrawalFailed() throws InsufficientCashException {
    sample.withdraw(20, 80);
  }

  /**
   * Test register contents are unchanged during an unsuccessful withdrawal.
   */
  @Test
  public void testWithdrawalFail4() throws InsufficientCashException {
    try {
      sample.withdraw(2, 80);
    } catch (InsufficientCashException e) {
      Map<Integer, Integer> y = new TreeMap<>();
      y = sample.getContents();
      assertEquals(true, sampleRegMap.equals(y));
    }
  }

  /**
   * Test register contents are changed to reflect new balance when successful withdrawal.
   */
  @Test
  public void testWithdrawalSuccess() throws InsufficientCashException {
    sample.withdraw(20, 0);
    Map<Integer, Integer> y = new TreeMap<>();
    y = sample.getContents();
    assertNotEquals(true, sampleRegMap.equals(y));
  }
}
