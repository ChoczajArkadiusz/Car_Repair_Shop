package pl.coderslab.dao;

import pl.coderslab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDao {
    private static final String sqlInsert = "INSERT INTO vehicles(manufacturer, model, year_of_production, plate_number, next_review_date) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE vehicles SET manufacturer=?, model=?, year_of_production=?, plate_number=?, next_review_date=? WHERE id=?";
    private static final String sqlLoadAll = "SELECT * FROM vehicles";
    private static final String sqlLoadById = "SELECT * FROM vehicles WHERE id=?";
    private static final String sqlDelete = "DELETE FROM vehicles WHERE id=?";


    public static void saveToDb(Vehicle vehicle) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (vehicle.getId() == 0) {
            String[] generatedColumns = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, generatedColumns);
            preparedStatement.setString(1, vehicle.getManufacturer());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYearOfProduction());
            preparedStatement.setString(4, vehicle.getPlateNumber());
            preparedStatement.setDate(5, vehicle.getNextReviewDate());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                vehicle.setId(rs.getLong(1));
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, vehicle.getManufacturer());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYearOfProduction());
            preparedStatement.setString(4, vehicle.getPlateNumber());
            preparedStatement.setDate(5, vehicle.getNextReviewDate());
            preparedStatement.setLong(6, vehicle.getId());
            preparedStatement.executeUpdate();
        }
    }


    public void delete(Vehicle vehicle) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (vehicle.getId() != 0) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, vehicle.getId());
            preparedStatement.executeUpdate();
            vehicle.setId(0L);
        }
    }


    public static Vehicle[] loadAll() throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        do {
            vehicles.add(extractObject(resultSet));
        }
        while (!resultSet.isLast());
        Vehicle[] arr = new Vehicle[vehicles.size()];
        arr = vehicles.toArray(arr);
        return arr;
    }


    public static Vehicle loadById(int id) throws SQLException {
        Connection connection = DbUtil.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlLoadById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return extractObject(resultSet);
    }


    private static Vehicle extractObject(ResultSet resultSet) throws SQLException {
        Vehicle extractedObject = new Vehicle();
        if (resultSet.next()) {
            extractedObject.setId(resultSet.getLong("id"));
            extractedObject.setManufacturer(resultSet.getString("manufacturer"));
            extractedObject.setModel(resultSet.getString("model"));
            extractedObject.setYearOfProduction(resultSet.getInt("year_of_production"));
            extractedObject.setPlateNumber(resultSet.getString("plate_number"));
            extractedObject.setNextReviewDate(resultSet.getDate("next_review_date"));
        }
        return extractedObject;
    }


}
