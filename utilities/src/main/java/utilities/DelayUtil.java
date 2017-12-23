package utilities;

import org.apache.commons.math3.random.RandomDataGenerator;

public class DelayUtil {
    private static final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    public static void randomDelay() {
        delay(randomDataGenerator.nextLong(2000,5000));
    }

    public static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
