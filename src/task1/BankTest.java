package task1;

import java.util.*;
import java.util.concurrent.*;

public class BankTest {

    public static void main(String[] args) throws Exception {
        int n = 200;
        Random rnd = new Random();
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            accounts.add(new Account(i, rnd.nextInt(10000) + 1));
        }

        long before = total(accounts);

        ExecutorService pool = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(3000);

        for (int i = 0; i < 3000; i++) {
            pool.execute(() -> {
                Account from = accounts.get(rnd.nextInt(n));
                Account to = accounts.get(rnd.nextInt(n));
                int amount = rnd.nextInt(200) + 1;
                bank.transfer(from, to, amount);
                latch.countDown();
            });
        }

        latch.await();
        pool.shutdown();

        long after = total(accounts);

        System.out.println(before);
        System.out.println(after);
    }

    private static long total(List<Account> accounts) {
        long sum = 0;
        for (Account a : accounts) {
            sum += a.getBalance();
        }
        return sum;
    }
}
