package pl.coderslab.dao;

import pl.coderslab.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {
    private static final String sqlInsert = "INSERT INTO employees(first_name, last_name, address, phone, note, man_hour_cost) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE employees SET first_name=?, last_name=?, address=?, phone=?, note=?, man_hour_cost=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM employees";
    private static final String sqlLoadById = "SELECT * FROM employees WHERE id=?";
    private static final String sqlDelete = "DELETE FROM employees WHERE id=?";


    public static void saveToDb(Employee employee) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (employee.getId() == 0) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setString(5, employee.getNote());
            preparedStatement.setDouble(6, employee.getManHourCost());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                employee.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setString(5, employee.getNote());
            preparedStatement.setDouble(6, employee.getManHourCost());
            preparedStatement.setLong(7, employee.getId());
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


    public static Employee[] loadAll() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            do {
                employees.add(extractObject(resultSet));
            }
            while (resultSet.next());
        }
        Employee[] arr = new Employee[employees.size()];
        arr = employees.toArray(arr);
        return arr;
    }


    public static Employee loadById(long id) throws SQLException {
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return extractObject(resultSet);
    }


    private static Employee extractObject(ResultSet resultSet) throws SQLException {
        Employee extractedObject = new Employee();
        extractedObject.setId(resultSet.getLong("id"));
        extractedObject.setFirstName(resultSet.getString("first_name"));
        extractedObject.setLastName(resultSet.getString("last_name"));
        extractedObject.setAddress(resultSet.getString("address"));
        extractedObject.setPhone(resultSet.getString("phone"));
        extractedObject.setNote(resultSet.getString("note"));
        extractedObject.setManHourCost(resultSet.getDouble("man_hour_cost"));
        return extractedObject;
    }


}
