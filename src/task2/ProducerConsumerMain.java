package task2;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerMain {

    public static void main(String[] args) {
        RingBuffer<String> buffer1 = new RingBuffer<>(50);
        RingBuffer<String> buffer2 = new RingBuffer<>(50);

        AtomicInteger producerId = new AtomicInteger(0);
        AtomicInteger translatorId = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {
            int id = producerId.incrementAndGet();
            Thread producer = new Thread(() -> {
                int msg = 0;
                while (true) {
                    buffer1.put("Thread #" + id + " generated message " + (++msg));
                }
            });
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            int id = translatorId.incrementAndGet();
            Thread translator = new Thread(() -> {
                while (true) {
                    String original = buffer1.take();
                    buffer2.put("Thread #" + id + " translated message: " + original);
                }
            });
            translator.setDaemon(true);
            translator.start();
        }

        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ") " + buffer2.take());
        }
    }
}

