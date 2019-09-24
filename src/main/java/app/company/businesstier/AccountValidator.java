package app.company.businesstier;

public class AccountValidator {
	public void validate(String userName, String password) throws Exception {
		if (!this.isValidateUsername(userName)) {
			throw new Exception("Validator.INVALID_USERNAME");
		}
		if (!this.isValidatePassword(password)) {
			throw new Exception("Validator.INVALID_PASSWORD");
		}

	}

	public boolean isValidateUsername(String userName) {
		boolean isValid = false;
		if (userName != null) {
			if (userName.charAt(0) >= 'A' && userName.charAt(0) <= 'Z') {
				isValid = true;
			}
		}
		return isValid;
	}

	public boolean isValidatePassword(String password) {
		boolean isValid = false;
		if (password != null) {
			if (password.length() >= 4 && password.length() <= 8) {
				isValid = true;
			}
		}
		return isValid;
	}

	public void validateAmount(Double amount) throws Exception {

		if (!isValidAmount(amount)) {
			throw new Exception("Validator.INVALID_AMOUNT");
		}
	}

	public boolean isValidAmount(Double amount) {
		boolean isValid = false;
		if (amount >= 50 && amount <= 200) {
			isValid = true;
		}
		return isValid;
	}

}
