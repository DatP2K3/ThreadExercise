//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TransactionSimulation simulation = new TransactionSimulation();
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++) {
            threads[i]= new Thread(simulation::performTransaction);
            threads[i].start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Final Balance of " + Thread.currentThread().getName() + ": " + simulation.balance);
        System.out.println("Final Balance2 of " + Thread.currentThread().getName() + ": " +(TransactionSimulation.INIT_BALANCE + simulation.credits - simulation.debits));
        System.out.println("Total Credits of " + Thread.currentThread().getName() + ": " + simulation.credits);
        System.out.println("Total Debits of " + Thread.currentThread().getName() + ": " +simulation.debits);
        long elapsedTime = endTime - startTime;
        System.out.println("Execution Time of " + Thread.currentThread().getName() + ": " + elapsedTime + " milliseconds");
    }
}