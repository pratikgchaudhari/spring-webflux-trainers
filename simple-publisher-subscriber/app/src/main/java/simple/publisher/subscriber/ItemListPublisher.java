package simple.publisher.subscriber;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

public class ItemListPublisher<T> implements Publisher<T> {

    private final List<T> data;
    private Boolean isCancelled;

    ItemListPublisher(List<T> data) {
        this.isCancelled = false;
        this.data = data;
    }

    @Override
    public void subscribe(Subscriber<? super T> s) {
        s.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                for (var i = 0; i < n; i++) {
                    if (isCancelled) {
                        break;
                    }
                    s.onNext(data.get(i));
                }
                s.onComplete();
            }

            @Override
            public void cancel() {
                isCancelled = true;
            }
        });
    }
}
