package tagroba2;
import java.util.concurrent.BlockingQueue;

public class Restaurant implements Runnable {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private BlockingQueue<Order> orderQueue;
    private Database database;

    public Restaurant(String name, String address, String phoneNumber, BlockingQueue<Order> orderQueue, Database database) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderQueue = orderQueue;
        this.database = database;
        this.id = database.addRestaurant(name, address, phoneNumber);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String orderId = name + "-Order-" + System.currentTimeMillis();
                String address = "Address " + (System.currentTimeMillis() % 3 + 1);
                Order order = new Order(orderId, id, -1, address);

                orderQueue.put(order);
                database.updateOrderStatus(order.getId(), "Pending");
                System.out.println("Restaurant " + name + " added: " + order);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Restaurant " + name + " is stopping.");
        }
    }
}