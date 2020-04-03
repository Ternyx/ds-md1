package com.github.ternyx.logic;

import java.util.Random;
import com.github.ternyx.structures.Queue;

/**
 * NumberCalls
 */
public class NumberCalls {
    private static final Random randGen = new Random();

    public static void simulateNumberCalls(final int numbers, final int maxDelayInSeconds) {
        Queue<String> numberQueue = new Queue<>();
        for (int i = 0; i < numbers; i++) {
            String phoneNumber = generateRandomPhoneNumber();
            System.out.format("%s is being enqueued\n", phoneNumber);
            numberQueue.enqueue(phoneNumber);
        }

        try {
            //Thread.sleep(1000);
            while (!numberQueue.isEmpty()) {
                int msSleep = randGen.nextInt(maxDelayInSeconds * 1000);
                Thread.sleep(msSleep);
                System.out.format("After a delay of %dms, number %s has received a reply\n", msSleep,
                        numberQueue.dequeue());
            }

        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private static String generateRandomPhoneNumber() {
        StringBuilder builder = new StringBuilder();
        builder.append(2);
        for (int i = 0; i < 7; i++) {
            builder.append(randGen.nextInt(10));
        }
        return builder.toString();
    }
}
