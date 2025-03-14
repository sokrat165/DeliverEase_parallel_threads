package tagroba2;
import java.util.concurrent.BlockingQueue;

public class DeliveryWorker implements Runnable {
    private int id;
    private String name;
    private String area;
    private BlockingQueue<Order> orderQueue;
    private Database database;

    public DeliveryWorker(String name, String area, BlockingQueue<Order> orderQueue, Database database) {
        this.name = name;
        this.area = area;
        this.orderQueue = orderQueue;
        this.database = database;
        this.id = database.addDeliveryWorker(name, area);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = orderQueue.take();
                System.out.println(name + " delivered: " + order);
                database.updateOrderStatus(order.getId(), "Delivered");
            }
        } catch (InterruptedException e) {
            System.out.println(name + " is stopping.");
        }
    }
}