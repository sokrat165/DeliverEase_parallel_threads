package tagroba2;
import java.sql.*;

public class Database {
    private  volatile  static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/delivery_system";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (project1.Database.class) {
                if (instance == null){
                    instance = new Database();
                }
            }
            
        }
        return instance;
    }

    public int addRestaurant(String name, String address, String phoneNumber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO restaurants (name, address, phone_number) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addDeliveryWorker(String name, String area) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO delivery_workers (name, area) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, area);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateOrderStatus(String orderId, String status) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE orders SET status = ? WHERE id = ?")) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, orderId);
            preparedStatement.executeUpdate();
            System.out.println("Order status updated: " + orderId + " -> " + status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}