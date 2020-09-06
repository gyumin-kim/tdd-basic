public class RegisterResult {
	private final boolean success;
	private final CardValidity validity;

	public RegisterResult(final boolean success, final CardValidity validity) {
		this.success = success;
		this.validity = validity;
	}

	public boolean isSuccess() {
		return success;
	}

	public CardValidity getValidity() {
		return validity;
	}

	public static RegisterResult error(final CardValidity validity) {
		return new RegisterResult(false, validity);
	}

	public static RegisterResult success() {
		return new RegisterResult(true, CardValidity.VALID);
	}
}
