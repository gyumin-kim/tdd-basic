import java.time.LocalDateTime;

public class AutoDebitInfo {
	private final String userId;
	private String cardNumber;
	private final LocalDateTime registTime;
	private LocalDateTime updateTime;

	public AutoDebitInfo(final String userId, final String cardNumber, final LocalDateTime registTime) {
		this.userId = userId;
		this.cardNumber = cardNumber;
		this.registTime = registTime;
		this.updateTime = registTime;
	}

	public String getUserId() {
		return userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public LocalDateTime getRegistTime() {
		return registTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void changeCardNumber(final String cardNumber) {
		this.cardNumber = cardNumber;
		this.updateTime = LocalDateTime.now();
	}
}
