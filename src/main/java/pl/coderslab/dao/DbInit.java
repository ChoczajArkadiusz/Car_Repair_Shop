package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DbInit {

    public static String sqlCustomers = "CREATE TABLE IF NOT EXISTS customers" +
            "(" +
            "id BIGINT AUTO_INCREMENT," +
            "first_name VARCHAR(100), " +
            "last_name VARCHAR(100), " +
            "birth_date DATE," +
            "PRIMARY KEY (id) " +
            ")";

    public static String sqlEmployees = "CREATE TABLE IF NOT EXISTS employees" +
            "(" +
            "id BIGINT AUTO_INCREMENT," +
            "first_name VARCHAR(100), " +
            "last_name VARCHAR(100), " +
            "address VARCHAR(255), " +
            "phone VARCHAR(20), " +
            "note VARCHAR(1000), " +
            "man_hour_cost DECIMAL(10,2)," +
            "PRIMARY KEY (id) " +
            ")";

    public static String sqlVehicles = "CREATE TABLE IF NOT EXISTS vehicles" +
            "(" +
            "id BIGINT AUTO_INCREMENT," +
            "manufacturer VARCHAR(100), " +
            "model VARCHAR(100), " +
            "year_of_production INT, " +
            "plate_number VARCHAR(20), " +
            "next_review_date DATE," +
            "PRIMARY KEY (id) " +
            ")";

    public static String sqlOrders = "CREATE TABLE IF NOT EXISTS orders" +
            "(" +
            "id BIGINT AUTO_INCREMENT," +
            "acceptance_date DATE," +
            "scheduled_start_date DATE," +
            "start_date DATE," +
            "employee_id BIGINT," +
            "problem_desc VARCHAR(2000), " +
            "repair_desc VARCHAR(2000), " +
            "status VARCHAR(255), " +
            "vehicle_id BIGINT," +
            "man_hours DECIMAL(10,2)," +
            "man_hour_cost DECIMAL(10,2)," +
            "parts_cost DECIMAL(10,2)," +
            "cost_for_customer DECIMAL(10,2)," +
            "PRIMARY KEY (id), " +
            "FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE " +
            ")";

    public static String sqlCustomersVehicles = "CREATE TABLE IF NOT EXISTS customers_vehicles" +
            "(" +
            "id BIGINT AUTO_INCREMENT," +
            "customer_id BIGINT," +
            "vehicle_id BIGINT," +
            "PRIMARY KEY (id), " +
            "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE " +
            ")";



    public static void createTableCustomers() {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtil.getConn();
            preparedStatement = connection.prepareStatement(sqlCustomers);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTableEmployees() {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtil.getConn();
            preparedStatement = connection.prepareStatement(sqlEmployees);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTableVehicles() {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtil.getConn();
            preparedStatement = connection.prepareStatement(sqlVehicles);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTableOrders() {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtil.getConn();
            preparedStatement = connection.prepareStatement(sqlOrders);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTableCustomersVehicles() {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtil.getConn();
            preparedStatement = connection.prepareStatement(sqlCustomersVehicles);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
