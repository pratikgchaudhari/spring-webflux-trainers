package simple.publisher.subscriber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public class ItemListPublisherTest {
    @Test
    void shouldPublisherSubscriberMethodsInExpectedOrder() {
        var integerListPublisher = new ItemListPublisher<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        var methodsInvoked = new ArrayList<String>();

        integerListPublisher.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                methodsInvoked.add("onSubscribe()");
                s.request(5);
            }

            @Override
            public void onNext(Integer integer) {
                methodsInvoked.add(String.format("onNext(%s)", integer));
            }

            @Override
            public void onError(Throwable t) {
                methodsInvoked.add(String.format("onError(%s)", t.getClass().getSimpleName()));
            }

            @Override
            public void onComplete() {
                methodsInvoked.add("onComplete()");
            }
        });

        var actual = methodsInvoked.toArray(new String[0]);
        var expected = new String[]{"onSubscribe()", "onNext(1)", "onNext(2)", "onNext(3)", "onNext(4)", "onNext(5)", "onComplete()"};

        Assertions.assertArrayEquals(expected, actual, new Supplier<String>() {
            @Override
            public String get() {
                var s = new StringBuilder();

                s.append(String.format("Actual: %s", String.join(",", actual)))
                        .append(" vs ")
                        .append(String.format("Expected: %s", String.join(",", expected)));

                return s.toString();
            }
        });
    }
}
