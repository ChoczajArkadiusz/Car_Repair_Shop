package pl.coderslab.dao;

import pl.coderslab.model.CustomerVehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerVehicleDao {
    private static final String sqlInsert = "INSERT INTO customers_vehicles(customer_id, vehicle_id) VALUES (?, ?)";
    private static final String sqlUpdate = "UPDATE customers_vehicles SET customer_id=?, vehicle_id=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM customers_vehicles";
    private static final String sqlLoadAllByCustomerId = "SELECT * FROM customers_vehicles WHERE customer_id=";
    private static final String sqlLoadallByVehicleId = "SELECT * FROM customers_vehicles WHERE vehicle_id=";
    private static final String sqlLoadById = "SELECT * FROM customers_vehicles WHERE id=?";
    private static final String sqlDelete = "DELETE FROM customers_vehicles WHERE id=?";


    public static void saveToDb(CustomerVehicle customerVehicle) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (customerVehicle.getId() == 0) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setLong(1, customerVehicle.getCustomerId());
            preparedStatement.setLong(2, customerVehicle.getVehicleId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                customerVehicle.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setLong(1, customerVehicle.getCustomerId());
            preparedStatement.setLong(2, customerVehicle.getVehicleId());
            preparedStatement.setLong(3, customerVehicle.getId());
            preparedStatement.executeUpdate();
        }
    }


    public static void delete(long id) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (id != 0) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }


    public static CustomerVehicle[] loadAll() throws SQLException {
        return loadAMultiple(sqlLoadAll);
    }

    public static CustomerVehicle[] loadAllByCustomerId(long id) throws SQLException {
        return loadAMultiple(sqlLoadAllByCustomerId + id);
    }

    public static CustomerVehicle[] loadallByVehicleId(long id) throws SQLException {
        return loadAMultiple(sqlLoadallByVehicleId + id);
    }


    public static CustomerVehicle[] loadAMultiple(String sqlQuery) throws SQLException {
        ArrayList<CustomerVehicle> customerVehicles = new ArrayList<>();
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            do {
                customerVehicles.add(extractObject(resultSet));
            }
            while (resultSet.next());
        }
        CustomerVehicle[] arr = new CustomerVehicle[customerVehicles.size()];
        arr = customerVehicles.toArray(arr);
        return arr;
    }


    public static CustomerVehicle loadById(long id) throws SQLException {
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return extractObject(resultSet);
    }


    private static CustomerVehicle extractObject(ResultSet resultSet) throws SQLException {
        CustomerVehicle extractedObject = new CustomerVehicle();
        extractedObject.setId(resultSet.getLong("id"));
        extractedObject.setCustomerId(resultSet.getLong("customer_id"));
        extractedObject.setVehicleId(resultSet.getLong("vehicle_id"));
        return extractedObject;
    }


}
