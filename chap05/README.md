# CHAPTER 5 — JUnit 5 기초

- `assertAll()`
    - 하나의 테스트에 여러 단언문이 있을 때, 어떤 단언문이 실패하면 그 뒤의 단언문들은 아예 실행되지 않는다. 모든 단언문을 실행하여 검증하고 싶다면 `assertAll()`을 사용한다.
    ```java
    assertAll(
      () -> assertEquals(3, 5/2),
      () -> assertEquals(4, 2*2),
      () -> assertEquals(6, 11/2)
    );
    ```
- JUnit은 테스트를 실행할 때, 해당 테스트 클래스의 객체를 매번 새롭게 생성한다.
- `@BeforeAll`과 `@AfterAll`은 정적 메서드에 붙여야 한다.
- 테스트 메서드의 순서를 가정해서 테스트를 작성하면 안된다.
    - 각 테스트 메서드는 서로 독립적으로 동작해야 한다.
    - 테스트 메서드가 서로 필드를 공유한다거나 실행 순서를 가정하여 동작하도록 하면 안된다.
- Maven, Gradle 사용 시 모든 테스트를 실행하는 방법
    - `mvn test` (`mvn package`도 가능; test 단계를 먼저 실행하므로)
    - `gradle test` (`gradle build`도 가능; test를 먼저 실행하므로)