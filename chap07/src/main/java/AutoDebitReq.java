public class AutoDebitReq {
	private final String userId;
	private final String cardNumber;

	public AutoDebitReq(final String userId, final String cardNumber) {
		this.userId = userId;
		this.cardNumber = cardNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}
}
