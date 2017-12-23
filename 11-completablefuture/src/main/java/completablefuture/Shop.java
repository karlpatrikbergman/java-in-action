package completablefuture;

import utilities.DelayUtil;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

class Shop {
    /**
     * The internal implementation of this method would query the shop’s database but probably also
     * perform other time-consuming tasks, such as contacting various other external services
     * (forexample, the shop’s suppliers or manufacturer-related promotional discounts)
     */
    double getPrice(String product) {
        DelayUtil.delay(5000);
        return calculatePrice(product);
    }

    CompletableFuture<Double> getPriceAsyc(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price =  getPrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    private double calculatePrice(String product) {
        DelayUtil.randomDelay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
