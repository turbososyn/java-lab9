package task1;

public class Account {
    private final int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
