package completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utilities.DelayUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class ShopTest {

    private final Shop shop = new Shop();

    @Test
    public void getPrice() {
        double price = shop.getPrice("Computer");
        log.info("Current computer price is  {}", price);
    }

    @Test
    public void getPriceAsync() throws ExecutionException, InterruptedException {
        Instant start = Instant.now();
        Future<Double> futurePrice = shop.getPriceAsyc("Computer");
        Instant end = Instant.now();
        long invocationTime = Duration.between(start,end).toMillis();
        log.info("Invocation of Shop.getPrice() took {} milli seconds", invocationTime);

        doSomethingElse();

        double price = futurePrice.get();
        log.info("Product price is {}", price);
    }

    private void doSomethingElse() {
        IntStream.rangeClosed(0,5)
                .boxed()
                .forEach(integer -> {
                    log.info("Doing something while waiting...");
                    DelayUtil.delay(500);
                });
    }
}
