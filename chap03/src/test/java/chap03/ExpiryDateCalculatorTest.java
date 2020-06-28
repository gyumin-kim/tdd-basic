package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

	@Test
	void 만원_납부하면_한달_뒤가_만료일이_됨() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2019, 4, 1));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 5, 5))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2019, 6, 5));
	}

	@Test
	void 납부일과_한달_뒤_일자가_같지_않음() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 1, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2019, 2, 28));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 5, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2019, 6, 30));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2020, 1, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2020, 2, 29));
	}

	@Test
	void 첫_납부일과_만료일의_일자가_같지_않을때_1만원_납부하면_첫_납부일_기준으로_다음_만료일_정함() {
		PayData payData = PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 31))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(10_000)
				.build();

		assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

		PayData payData2 = PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 30))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(10_000)
				.build();

		assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

		PayData payData3 = PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 5, 31))
				.billingDate(LocalDate.of(2019, 6, 30))
				.payAmount(10_000)
				.build();

		assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
	}

	@Test
	void 이만원_이상_납부하면_비례해서_만료일_계산() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(20_000)
						.build(),
				LocalDate.of(2019, 5, 1));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(30_000)
						.build(),
				LocalDate.of(2019, 6, 1));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(50_000)
						.build(),
				LocalDate.of(2019, 8, 1));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(70_000)
						.build(),
				LocalDate.of(2019, 10, 1));
	}

	@Test
	void 첫_납부일과_만료일의_일자가_같지_않을때_2만원_이상_납부하면_첫_납부일_기준으로_다음_만료일_정함() {
		assertExpiryDate(
				PayData.builder()
						.firstBillingDate(LocalDate.of(2019, 1, 31))
						.billingDate(LocalDate.of(2019, 2, 28))
						.payAmount(20_000)
						.build(),
				LocalDate.of(2019, 4, 30));
		assertExpiryDate(
				PayData.builder()
						.firstBillingDate(LocalDate.of(2019, 1, 31))
						.billingDate(LocalDate.of(2019, 2, 28))
						.payAmount(40_000)
						.build(),
				LocalDate.of(2019, 6, 30));
		assertExpiryDate(
				PayData.builder()
						.firstBillingDate(LocalDate.of(2019, 3, 31))
						.billingDate(LocalDate.of(2019, 4, 30))
						.payAmount(30_000)
						.build(),
				LocalDate.of(2019, 7, 31));
	}

	@Test
	void 십만원을_납부하면_1년_제공() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 1, 28))
						.payAmount(100_000)
						.build(),
				LocalDate.of(2020, 1, 28));
	}

	@Test
	void 윤달의_마지막_날에_십만원을_납부() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2020, 2, 29))
						.payAmount(100_000)
						.build(),
				LocalDate.of(2021, 2, 28));
	}

	@Test
	void 십삼만원을_납부하면_1년_3개월_뒤가_만료일() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 1, 31))
						.payAmount(130_000)
						.build(),
				LocalDate.of(2020, 4, 30));
	}

	private void assertExpiryDate(final PayData payData, final LocalDate expectedExpiryDate) {
		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
		assertEquals(expectedExpiryDate, realExpiryDate);
	}
}
