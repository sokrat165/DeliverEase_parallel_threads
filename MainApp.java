package tagroba2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

        Database database = Database.getInstance();

        ExecutorService deliveryExecutor = Executors.newFixedThreadPool(3);

        Thread restaurant1 = new Thread(new Restaurant("McDonald's", "123 Main St", "555-1234", orderQueue, database));
        Thread restaurant2 = new Thread(new Restaurant("KFC", "456 Elm St", "555-5678", orderQueue, database));

        restaurant1.start();
        restaurant2.start();

        for (int i = 1; i <= 3; i++) {
            deliveryExecutor.submit(DeliveryWorkerFactory.createDeliveryWorker("Worker " + i, "Area " + i, orderQueue, database));
        }

        Thread.sleep(10000);

        restaurant1.interrupt();
        restaurant2.interrupt();

        deliveryExecutor.shutdown();
        deliveryExecutor.awaitTermination(5, TimeUnit.SECONDS);

        database.close();
    }
}