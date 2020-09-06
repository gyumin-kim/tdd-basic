import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {
	private AutoDebitRegister register;

	@BeforeEach
	void setUp() {
		CardNumberValidator validator = new CardNumberValidator();
		AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
		register = new AutoDebitRegister(validator, repository);
	}

	@Test
	void validCard() {
		// given
		String validCardNumber = "1234123412341234";
		AutoDebitReq req = new AutoDebitReq("user1", validCardNumber);

		// when
		RegisterResult result = this.register.register(req);

		// then
		assertEquals(CardValidity.VALID, result.getValidity());
	}

	@Test
	void theftCard() {
		// given
		String theftCardNumber = "1234123412341234";
		AutoDebitReq req = new AutoDebitReq("user1", theftCardNumber);

		// when
		RegisterResult result = this.register.register(req);

		// then
		assertEquals(CardValidity.THEFT, result.getValidity());
	}
}
