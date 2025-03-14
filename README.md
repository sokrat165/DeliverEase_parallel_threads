# DeliverEase_parallel_threads
DeliverEase is a multi-threaded Java application that simulates a food delivery system. It demonstrates the use of several design patterns, including Producer-Consumer, Singleton, and Factory Method, to manage restaurants, delivery workers, and orders efficiently.

---

## Features

- **Restaurants (Producers):**
  - Generate orders and add them to a shared queue.
  - Simulate order preparation with a delay.

- **Delivery Workers (Consumers):**
  - Pick up orders from the queue and deliver them.
  - Simulate delivery time with a delay.

- **Database:**
  - Tracks restaurants, delivery workers, and orders.
  - Updates order statuses (Pending, In Progress, Delivered).

- **Thread Management:**
  - Uses `ExecutorService` to manage delivery workers.
  - Ensures safe shutdown of threads.

---

## Design Patterns Used

1. **Producer-Consumer Pattern:**
   - Restaurants produce orders and add them to a `BlockingQueue`.
   - Delivery workers consume orders from the queue.

2. **Singleton Pattern:**
   - The `Database` class is implemented as a Singleton to ensure a single connection to the database.

3. **Factory Method Pattern:**
   - The `DeliveryWorkerFactory` class is used to create different types of delivery workers (e.g., bike, car).

4. **Observer Pattern:**
   - The `Order` class notifies listeners (e.g., restaurants, delivery workers) when the order status changes.
