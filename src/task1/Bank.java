package task1;

public class Bank {

    public void transfer(Account from, Account to, int amount) {
        if (from == to || amount <= 0) return;

        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}
