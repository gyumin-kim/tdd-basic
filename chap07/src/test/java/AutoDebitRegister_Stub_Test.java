import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
	private AutoDebitRegister register;
	private StubCardNumberValidator stubValidator;
	private StubAutoDebitInfoRepository stubRepository;

	@BeforeEach
	void setUp() {
		stubValidator = new StubCardNumberValidator();
		stubRepository = new StubAutoDebitInfoRepository();
		register = new AutoDebitRegister(stubValidator, stubRepository);
	}

	@Test
	void invalidCard() {
		// given
		stubValidator.setInvalidNo("111122223333");
		AutoDebitReq req = new AutoDebitReq("user1", "111122223333");

		// when
		RegisterResult result = this.register.register(req);

		// then
		assertEquals(CardValidity.INVALID, result.getValidity());
	}

	@Test
	void theftCard() {
		// given
		stubValidator.setTheftNo("1234567890123456");
		AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");

		// when
		RegisterResult result = this.register.register(req);

		// then
		assertEquals(CardValidity.THEFT, result.getValidity());
	}
}
