package tagroba2;

import java.util.concurrent.BlockingQueue;

public class DeliveryWorkerFactory {
    public static DeliveryWorker createDeliveryWorker(String name, String area,
     BlockingQueue<Order> orderQueue, Database database) {
        return new DeliveryWorker(name, area, orderQueue, database);
    }
}