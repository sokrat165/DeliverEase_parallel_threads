package tagroba2;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private int restaurantId;
    private int workerId;
    private String address;
    private List<OrderListener> listeners = new ArrayList<>();

    public Order(String id, int restaurantId, int workerId, String address) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.workerId = workerId;
        this.address = address;
    }

    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    public void setStatus(String status) {
        for (OrderListener listener : listeners) {
            listener.onOrderStatusCh
            anged(this, status);
        }
    }


    public String getId() {
        return id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + " | Restaurant ID: " + restaurantId + " | Worker ID: " + workerId + " | Address: " + address;
    }
}