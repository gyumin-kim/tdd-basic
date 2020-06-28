package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
	public LocalDate calculateExpiryDate(final PayData payData) {
		int payAmount = payData.getPayAmount();
		int addedMonths = payAmount == 100_000 ? 12 : payAmount / 10_000;
		if (payData.getFirstBillingDate() != null) {
			return expiryDateUsingFirstBillingDate(payData, addedMonths);
		}
		return payData.getBillingDate().plusMonths(addedMonths);
	}

	private LocalDate expiryDateUsingFirstBillingDate(final PayData payData, final int addedMonths) {
		LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
		if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
			final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
			final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
			if (dayLenOfCandiMon < dayOfFirstBilling) {
				return candidateExp.withDayOfMonth(dayLenOfCandiMon);
			}
			return candidateExp.withDayOfMonth(dayOfFirstBilling);
		}
		return candidateExp;
	}

	private boolean isSameDayOfMonth(final LocalDate date1, final LocalDate date2) {
		return date1.getDayOfMonth() == date2.getDayOfMonth();
	}

	private int lastDayOfMonth(final LocalDate date) {
		return YearMonth.from(date).lengthOfMonth();
	}
}
