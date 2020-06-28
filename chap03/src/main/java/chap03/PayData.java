package chap03;

import java.time.LocalDate;

public class PayData {
	private LocalDate firstBillingDate;
	private LocalDate billingDate;
	private int payAmount;

	public PayData() {
	}

	public PayData(final LocalDate firstBillingDate, final LocalDate billingDate, final int payAmount) {
		this.firstBillingDate = firstBillingDate;
		this.billingDate = billingDate;
		this.payAmount = payAmount;
	}

	public LocalDate getFirstBillingDate() {
		return firstBillingDate;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private final PayData data = new PayData();

		public Builder firstBillingDate(final LocalDate firstBillingDate) {
			data.firstBillingDate = firstBillingDate;
			return this;
		}
		public Builder billingDate(final LocalDate billingDate) {
			data.billingDate = billingDate;
			return this;
		}
		public Builder payAmount(final int payAmount) {
			data.payAmount = payAmount;
			return this;
		}
		public PayData build() {
			return data;
		}
	}
}
