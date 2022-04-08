package test.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceAndDiscontFeeWhenPositiveAmount() {

		double amount = 200.00;
		double expectedValue = 196.00;
		Account acc = AccountFactory.createdEmptyAccount();

		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}

	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		double expectedValue = 100.00;
		Account acc = AccountFactory.createAccount(expectedValue);
		double amount = -200.00;

		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}

	@Test
	public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {

		double expectedValue = 0.0;
		double initialBalance = 800.00;
		Account acc = AccountFactory.createAccount(initialBalance);
		double result = acc.fullWithdraw();

		Assertions.assertTrue(expectedValue == acc.getBalance());
		Assertions.assertTrue(result == initialBalance);
	}
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		Account acc = AccountFactory.createAccount(800.00);
		acc.withdraw(500.00);
		
		Assertions.assertEquals(300.0, acc.getBalance());
	}
	
	@Test
	public void whitdrawShouldThrowExcepitionsWhenInsufficientBalace() {
		
		Assertions.assertThrows(IllegalArgumentException.class,() ->{
			Account acc = AccountFactory.createAccount(800.00);
			acc.withdraw(801.00);
		});
	}
}
