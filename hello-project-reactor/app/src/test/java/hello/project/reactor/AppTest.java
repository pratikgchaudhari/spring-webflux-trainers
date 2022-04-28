package hello.project.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class AppTest {


    App app = new App();

    @Test
    void fluxIsCreated() {
        Flux<Integer> result = app.createFlux();
        StepVerifier.create(result)
                .expectNext(5, 6, 7, 8)
                .verifyComplete();
    }

    @Test
    void reactiveStreamsAreImmutable() {
        Flux<String> result = app.makeUpperCase();
        StepVerifier.create(result)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();
    }
}