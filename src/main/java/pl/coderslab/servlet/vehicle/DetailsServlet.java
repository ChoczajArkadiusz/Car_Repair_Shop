package pl.coderslab.servlet.vehicle;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.CustomerVehicleDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.CustomerVehicle;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VehiclesDetailsServlet", urlPatterns = "/vehicles/details")
public class DetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("vehicleId");
        long id = 0;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
        }
        try {
            Vehicle vehicle = VehicleDao.loadById(id);
            request.getSession().setAttribute("vehicleInSession", vehicle);
            try {
                CustomerVehicle[] customerVehicles = CustomerVehicleDao.loadallByVehicleId(vehicle.getId());
                Customer[] customers = new Customer[customerVehicles.length];
                int counter = 0;
                for (CustomerVehicle customerVehicle : customerVehicles) {
                    try {
                        customers[counter++] = CustomerDao.loadById(customerVehicle.getCustomerId());
                        request.setAttribute("customers", customers);
                    } catch (SQLException e) {
                        throw new ServletException("Error while fetching customer with id " + id + ". ", e);
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Error while fetching owners of vehicles. ", e);
            }
            request.setAttribute("vehicle", vehicle);
            getServletContext().getRequestDispatcher("/vehicles/details.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching vehicle with id " + id + ". ", e);
        }
    }


}
