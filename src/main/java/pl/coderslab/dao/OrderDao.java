package pl.coderslab.dao;

import pl.coderslab.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao {
    private static final String sqlInsert = "INSERT INTO " +
            "orders(acceptance_date, scheduled_start_date, start_date, employee_id, problem_desc, repair_desc," +
            " status, vehicle_id, man_hours, man_hour_cost, parts_cost, cost_for_customer) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE orders SET " +
            "acceptance_date=?, scheduled_start_date=?, start_date=?, employee_id=?, problem_desc=?, repair_desc=?" +
            "status=?, vehicle_id=?, man_hours=?, man_hour_cost=?, parts_cost=?, cost_for_customer=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM orders";
    private static final String sqlLoadById = "SELECT * FROM orders WHERE id=?";
    private static final String sqlDelete = "DELETE FROM orders WHERE id=?";


    public static void saveToDb(Order orders) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (orders.getId() == 0) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setDate(1, orders.getAcceptanceDate());
            preparedStatement.setDate(2, orders.getScheduledStartDate());
            preparedStatement.setDate(3, orders.getStartDate());
            preparedStatement.setLong(4, orders.getEmployeeId());
            preparedStatement.setString(5, orders.getProblemDescription());
            preparedStatement.setString(6, orders.getRepairDescription());
            preparedStatement.setString(7, orders.getStatus());
            preparedStatement.setLong(8, orders.getVehicleId());
            preparedStatement.setDouble(9, orders.getManHours());
            preparedStatement.setDouble(10, orders.getManHourCost());
            preparedStatement.setDouble(11, orders.getPartsCost());
            preparedStatement.setDouble(12, orders.getCostForCustomer());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                orders.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setDate(1, orders.getAcceptanceDate());
            preparedStatement.setDate(2, orders.getScheduledStartDate());
            preparedStatement.setDate(3, orders.getStartDate());
            preparedStatement.setLong(4, orders.getEmployeeId());
            preparedStatement.setString(5, orders.getProblemDescription());
            preparedStatement.setString(6, orders.getRepairDescription());
            preparedStatement.setString(7, orders.getStatus());
            preparedStatement.setLong(8, orders.getVehicleId());
            preparedStatement.setDouble(9, orders.getManHours());
            preparedStatement.setDouble(10, orders.getManHourCost());
            preparedStatement.setDouble(11, orders.getPartsCost());
            preparedStatement.setDouble(12, orders.getCostForCustomer());
            preparedStatement.setLong(13, orders.getId());
            preparedStatement.executeUpdate();
        }
    }


    public void delete(Order orders) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (orders.getId() != 0) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, orders.getId());
            preparedStatement.executeUpdate();
            orders.setId(0L);
        }
    }


    public static Order[] loadAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        do {
            orders.add(extractObject(resultSet));
        }
        while (!resultSet.isLast());
        Order[] arr = new Order[orders.size()];
        arr = orders.toArray(arr);
        return arr;
    }


    public static Order loadById(int id) throws SQLException {
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return extractObject(resultSet);
    }


    private static Order extractObject(ResultSet resultSet) throws SQLException {
        Order extractedObject = new Order();
        if (resultSet.next()) {
            extractedObject.setId(resultSet.getLong("id"));
            extractedObject.setAcceptanceDate(resultSet.getDate("acceptance_date"));
            extractedObject.setScheduledStartDate(resultSet.getDate("scheduled_start_date"));
            extractedObject.setStartDate(resultSet.getDate("start_date"));
            extractedObject.setEmployeeId(resultSet.getLong("employee_id"));
            extractedObject.setProblemDescription(resultSet.getString("problem_desc"));
            extractedObject.setRepairDescription(resultSet.getString("repair_desc"));
            extractedObject.setRepairDescription(resultSet.getString("status"));
            extractedObject.setVehicleId(resultSet.getLong("vehicle_id"));
            extractedObject.setManHours(resultSet.getDouble("man_hours"));
            extractedObject.setManHourCost(resultSet.getDouble("man_hour_cost"));
            extractedObject.setPartsCost(resultSet.getDouble("parts_cost"));
            extractedObject.setCostForCustomer(resultSet.getDouble("cost_for_customer"));
        }
        return extractedObject;
    }


}
