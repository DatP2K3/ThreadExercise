import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionSimulation {
    static final int INIT_BALANCE = 50;
    static final int NUM_TRANS = 1000000;
    int balance = INIT_BALANCE;
    int credits = 0;
    int debits = 0;
    private final Semaphore semaphore = new Semaphore(1);

    public void performTransaction() {
        Random random = new Random();
        try {
            // Đợi đến khi có giấy phép
            semaphore.acquire();

            for (int i = 0; i < NUM_TRANS; i++) {
                int v = random.nextInt(NUM_TRANS);
                if (random.nextInt(2) == 0) {
                    // Credit transaction
                    balance += v;
                    credits += v;
                } else {
                    // Debit transaction
                    balance -= v;
                    debits += v;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Giải phóng giấy phép cho các luồng khác khi hoàn thành
            semaphore.release();
        }
    }
}
