package hello.project.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class FluxTest {

    @Test
    void shouldCreateFluxUsingTheJustOperator() {
        final var fluxTest = Flux.just(1, 2, 3, 4);

        StepVerifier.create(fluxTest)
                .expectNextSequence(Arrays.asList(1, 2, 3, 4))
                .expectComplete()
                .verify();
    }

    @Test
    void shouldCreateFluxUsingTheEmptyOperator() {
        final var fluxTest = Flux.empty();

        StepVerifier.create(fluxTest)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldCreateFluxUsingTheErrorOperator() {
        final var fluxTest = Flux.error(new Exception("Test Error"));

        StepVerifier.create(fluxTest)
                .expectErrorMessage("Test Error")
                .verify();
    }

    @Test
    void shouldCreateFluxUsingTheNeverOperator() {
        final var fluxTest = Flux.never();

        StepVerifier.create(fluxTest.timeout(Duration.ofSeconds(1)))
                .verifyError(TimeoutException.class);
    }

    @Test
    void shouldCreateFluxUsingTheFromOperator() {
        final var fluxTest = Flux
                .from(Flux.range(1, 5));

        StepVerifier.create(fluxTest)
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldTransformTheContentsOfFluxUsingTheMapOperator() {
        final var fluxTest = Flux
                .fromIterable(Arrays.asList(1, 2, 3, 4))
                .map(item -> item * item);

        StepVerifier.create(fluxTest)
                .expectNextSequence(Arrays.asList(1, 4, 9, 16))
                .verifyComplete();
    }

    @Test
    void shouldTransformTheContentsOfFluxUsingTheFilterOperator() {
        final var fluxTest = Flux
                .from(Flux.range(1, 10))
                .filter(item -> (item > 2 && item < 8));

        StepVerifier.create(fluxTest)
                .expectNextSequence(Arrays.asList(3, 4, 5, 6, 7))
                .verifyComplete();
    }

    @Test
    void shouldRetrieveASpecificItemUsingTheElementAtOperator() {
        final var fluxTest = Flux
                .just(1, 2, 3, 4, 5)
                .elementAt(3);

        StepVerifier.create(fluxTest)
                .expectNext(4)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldRetrieveTheLastItemUsingTheLastOperator() {
        final var fluxTest = Flux
                .from(Flux.range(1, 100))
                .last();

        StepVerifier.create(fluxTest)
                .expectNext(100)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldMergeAFluxOfIntegersWithAFluxContainingTheirSquaresUsingTheMergeWithOperator() {
        final var fluxTest = Flux
                .from(Flux.range(1, 3))
                .mergeWith(Flux.range(1, 3).map(item -> item * item));

        StepVerifier.create(fluxTest)
                .expectNextSequence(Arrays.asList(1, 2, 3, 1, 4, 9))
                .expectComplete()
                .verify();
    }

    @Test
    void shouldCombineMultipleFluxOfIntegers() {
        var flux = Flux.from(Flux.range(1, 3))
                .zipWith(Flux.range(4, 3).map(Math::sqrt));

        flux.doOnNext(System.out::println).subscribe();
    }

    @Test
    void shouldDoSomethingOnSubscription() {
        var flux = Flux.just(1, 2, 3)
                .doOnSubscribe(subscription -> subscription.request(1))
                .doOnNext(System.out::println)
                .subscribe();
    }

    @Test
    void shouldDoSomethingOnRequest() {
        var flux = Flux.just(1, 2, 3)
                .doOnRequest(System.out::println)
                .doOnNext(System.out::println)
                .subscribe();
    }

    @Test
    void shouldDoSomethingOnError() {
        var flux = Flux.error(new Exception("Test Exception"))
                .doOnError(e -> System.out.println("Encountered error while processing request"));

        StepVerifier.create(flux)
                .expectErrorMessage("Test Exception")
                .verify();
    }

    @Test
    void shouldDoSomethingOnTermination() {
        var flux = Flux.just(1, 2, 3)
                .doOnTerminate(() -> System.out.println("The Flux has terminated"));

        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldDoSomethingOnFinally() {
        var flux = Flux
                .error(new Exception("Internal Server Error"))
                //                .just(1,2,3)
                .doOnComplete(() -> System.out.println("Flux has completed successfully"))
                .doOnError(e -> {
                    System.out.println("Flux has failed with an error: " + e.getMessage());
                })
                .doFinally(signalType -> {
                    switch (signalType.toString()) {
                        case "onComplete":
                            System.out.println("Flux has completed successfully");
                            break;
                        case "onError":
                            System.out.println("Flux has failed with an error");
                            break;
                    }
                });

        StepVerifier.create(flux)
//                .expectNextCount(3)
//                .verifyComplete();
                .expectErrorMessage("Internal Server Error")
                .verify();
    }

    @Test
    void shouldDoSomethingOnCancel() {
        var flux = Flux.just(1, 2, 3)
                .doOnCancel(() -> System.out.println("The operation is cancelled"))
                .doOnNext(System.out::println)
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.cancel();
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
