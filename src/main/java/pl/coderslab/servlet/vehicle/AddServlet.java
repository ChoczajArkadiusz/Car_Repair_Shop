package pl.coderslab.servlet.vehicle;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "VehiclesAddServlet", urlPatterns = "/vehicles/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        String yearOfProductionParam = request.getParameter("yearOfProduction");
        String plateNumber = request.getParameter("plateNumber");
        String nextReviewDateParam = request.getParameter("nextReviewDate");
        Date nextReviewDate = Date.valueOf("1970-01-01");

        Vehicle vehicle = new Vehicle();
        if (manufacturer != null && !manufacturer.equals("") &&
                model != null && !model.equals("") &&
                yearOfProductionParam != null && !yearOfProductionParam.equals("") &&
                plateNumber != null && !plateNumber.equals("")) {
            vehicle.setManufacturer(manufacturer);
            vehicle.setModel(model);
            vehicle.setYearOfProduction(Integer.valueOf(yearOfProductionParam));
            vehicle.setPlateNumber(plateNumber);
            if (nextReviewDateParam != null && !nextReviewDateParam.equals("")) {
                nextReviewDate = Date.valueOf(nextReviewDateParam);
            }
            vehicle.setNextReviewDate(nextReviewDate);
        }
        try {
            VehicleDao.saveToDb(vehicle);
            request.setAttribute("showInfoAdded", 1);
            getServletContext().getRequestDispatcher("/vehicles/add.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while adding vehicles. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("/null/vehicles/add.jsp");
    }


}
