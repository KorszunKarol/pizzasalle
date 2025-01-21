package com.pizzisalle.service;

import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.constants.Delegations;

import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String DB_URL = "jdbc:sqlite:pizzisalle.db";

    private DatabaseManager() {
        initializeDatabase();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                        "phone TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "address TEXT NOT NULL," +
                        "age INTEGER NOT NULL," +
                        "delegation TEXT NOT NULL," +
                        "first_order BOOLEAN DEFAULT true" +
                        ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS orders (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "customer_phone TEXT NOT NULL," +
                        "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "total_amount DECIMAL(10,2) NOT NULL," +
                        "FOREIGN KEY (customer_phone) REFERENCES customers(phone)" +
                        ")");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private String readSchemaFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("db/schema.sql"))))) {
            StringBuilder schema = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                schema.append(line).append("\n");
            }
            return schema.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read schema file", e);
        }
    }

    public Customer findCustomerByPhone(String phone) {
        String sql = "SELECT * FROM customers WHERE phone = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getInt("age"),
                    Delegations.valueOf(rs.getString("delegation")),
                    rs.getBoolean("first_order")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find customer", e);
        }
    }

    public void saveCustomer(Customer customer) {
        String sql = """
            INSERT INTO customers (name, phone, address, first_order, delegation, age)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getAddress());
            pstmt.setBoolean(4, customer.isFirstOrder());
            pstmt.setString(5, customer.getDelegation().toString());
            pstmt.setInt(6, customer.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save customer", e);
        }
    }

    public void updateCustomerFirstOrder(String phone) {
        String sql = "UPDATE customers SET first_order = 0 WHERE phone = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update customer first order status", e);
        }
    }

    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders (customer_phone, total_amount) VALUES ((SELECT phone FROM customers WHERE phone = ?), ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getCustomer().getPhone());
            pstmt.setDouble(2, order.calculateTotal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save order", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}