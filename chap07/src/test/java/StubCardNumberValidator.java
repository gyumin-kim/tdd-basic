public class StubCardNumberValidator extends CardNumberValidator {
	private String invalidNo;
	private String theftNo;

	public void setInvalidNo(final String invalidNo) {
		this.invalidNo = invalidNo;
	}

	public void setTheftNo(final String theftNo) {
		this.theftNo = theftNo;
	}

	@Override
	public CardValidity validate(final String cardNumber) {
		if (invalidNo != null && invalidNo.equals(cardNumber)) {
			return CardValidity.INVALID;
		}
		if (theftNo != null && theftNo.equals(cardNumber)) {
			return CardValidity.THEFT;
		}
		return CardValidity.VALID;
	}
}
