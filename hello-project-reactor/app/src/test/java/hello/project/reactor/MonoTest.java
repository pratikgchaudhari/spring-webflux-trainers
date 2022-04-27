package hello.project.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

class MonoTest {

    @Test
    void shouldCreateMonoUsingTheJustOperator() {
        final var testMono = Mono.just("Test");
        StepVerifier
                .create(testMono)
                .expectNext("Test")
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingTheJustOrEmptyOperatorForANullValue() {
        final var testMono = Mono.justOrEmpty(null);
        StepVerifier
                .create(testMono)
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingTheJustOrEmptyOperatorForANonNullValue() {
        final var testMono = Mono.justOrEmpty("Test");
        StepVerifier
                .create(testMono)
                .expectNext("Test")
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingTheJustOrEmptyOperatorForAnEmptyOptionalValue() {
        final var testMono = Mono.justOrEmpty(Optional.empty());
        StepVerifier
                .create(testMono)
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingTheJustOrEmptyOperatorForANullOptionalValue() {
        final var testMono = Mono.justOrEmpty(Optional.ofNullable(null));
        StepVerifier
                .create(testMono)
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingTheJustOrEmptyOperatorForANonNullOptionalValue() {
        final var testMono = Mono.justOrEmpty(Optional.of("Test"));
        StepVerifier
                .create(testMono)
                .expectNext("Test")
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingEmptyOperator() {
        final var testMono = Mono.empty();
        StepVerifier
                .create(testMono)
                .verifyComplete();
    }

    @Test
    void shouldCreateMonoUsingErrorOperator() {
        final var testMono = Mono.error(new Exception("Test Error"));
        StepVerifier
                .create(testMono)
                .expectErrorMessage("Test Error")
                .verify();
    }

    @Test
    void shouldCreateMonoUsingNeverOperator() {
        final var testMono = Mono.never();
        StepVerifier
                .create(testMono.timeout(Duration.ofSeconds(1)))
                .verifyError(TimeoutException.class);
    }

    @Test
    void shouldCreateMonoUsingDelayOperator() {

        StepVerifier
                .withVirtualTime(() -> Mono.delay(Duration.ofSeconds(10)))
                .thenAwait(Duration.ofSeconds(10))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldTransformMonoUsingTheMapOperator() {

        final var testMono = Mono.just(4).map(Math::sqrt);

        StepVerifier
                .create(testMono)
                .expectNext(2.0)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldTransformErrorMonoUsingTheOnErrorMap() {

        final var testMono = Mono
                .error(new Exception("Internal Server Error"))
                .onErrorMap(genericError -> new ApplicationFailureException("Oops! Something went wrong on our side. We'll ask our engineers to take a look."));

        StepVerifier
                .create(testMono)
                .expectErrorMessage("Oops! Something went wrong on our side. We'll ask our engineers to take a look.")
                .verify();
    }
}
