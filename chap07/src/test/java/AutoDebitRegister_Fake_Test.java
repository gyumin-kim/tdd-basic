import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Fake_Test {
	private AutoDebitRegister register;
	private StubCardNumberValidator cardNumberValidator;
	private MemoryAutoDebitInfoRepository repository;

	@BeforeEach
	void setUp() {
		cardNumberValidator = new StubCardNumberValidator();
		repository = new MemoryAutoDebitInfoRepository();
		register = new AutoDebitRegister(cardNumberValidator, repository);
	}

	@Test
	void alreadyRegistered_InfoUpdated() {
		// given
		repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));
		AutoDebitReq req = new AutoDebitReq("user1", "123456789012");

		// when
		RegisterResult result = this.register.register(req);

		// then
		AutoDebitInfo saved = repository.findOne("user1");
		assertEquals("123456789012", saved.getCardNumber());
	}

	@Test
	void notYetRegistered_newInfoRegistered() {
		// given
		AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");

		// when
		RegisterResult result = this.register.register(req);

		// then
		AutoDebitInfo saved = repository.findOne("user1");
		assertEquals("1234123412341234", saved.getCardNumber());
	}
}
