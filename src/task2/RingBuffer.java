package task2;

public class RingBuffer<T> {
    private final Object[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public RingBuffer(int capacity) {
        data = new Object[capacity];
    }

    public synchronized void put(T value) {
        while (size == data.length) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T take() {
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        T value = (T) data[head];
        head = (head + 1) % data.length;
        size--;
        notifyAll();
        return value;
    }
}
