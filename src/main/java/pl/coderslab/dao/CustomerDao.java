package pl.coderslab.dao;

import pl.coderslab.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDao {
    private static final String sqlInsert = "INSERT INTO customers(first_name, last_name, birth_date) VALUES (?, ?, ?)";
    private static final String sqlUpdate = "UPDATE customers SET first_name=?, last_name=?, birth_date=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM customers";
    private static final String sqlLoadById = "SELECT * FROM customers WHERE id=?";
    private static final String sqlDelete = "DELETE FROM customers WHERE id=?";


    public static void saveToDb(Customer customer) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (customer.getId() == 0) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthDate());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthDate());
            preparedStatement.setLong(4, customer.getId());
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


    public static Customer[] loadAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            do {
                customers.add(extractObject(resultSet));
            }
            while (resultSet.next());
        }
        Customer[] arr = new Customer[customers.size()];
        arr = customers.toArray(arr);
        return arr;
    }


    public static Customer loadById(long id) throws SQLException {
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return extractObject(resultSet);
    }


    private static Customer extractObject(ResultSet resultSet) throws SQLException {
        Customer extractedObject = new Customer();
        extractedObject.setId(resultSet.getLong("id"));
        extractedObject.setFirstName(resultSet.getString("first_name"));
        extractedObject.setLastName(resultSet.getString("last_name"));
        extractedObject.setBirthDate(resultSet.getDate("birth_date"));
        return extractedObject;
    }


}
