package app.company.presentationtier;

import org.junit.Assert;
import org.junit.Test;

import app.company.businesstier.AccountValidator;

public class AccountTest {
	AccountValidator validator = new AccountValidator();

	@Test
	public void inValidUserName() {
		Assert.assertFalse(validator.isValidateUsername("jack"));
	}

	@Test
	public void inValidPassword() {
		Assert.assertFalse(validator.isValidatePassword("435"));
	}
	@Test
	public void inValidAmount(){
		Assert.assertFalse(validator.isValidAmount(320.00));
	}
}
