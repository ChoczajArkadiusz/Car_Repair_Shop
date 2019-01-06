package pl.coderslab.servlet.customer;

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

@WebServlet(name = "CustomersDetailsServlet", urlPatterns = "/customers/details")
public class DetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("customerId");
        long id = 0;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
        }
        try {
            Customer customer = CustomerDao.loadById(id);
            request.getSession().setAttribute("customerInSession", customer);
            try {
                CustomerVehicle[] customerVehicles = CustomerVehicleDao.loadAllByCustomerId(customer.getId());
                Vehicle[] vehicles = new Vehicle[customerVehicles.length];
                int counter = 0;
                for (CustomerVehicle customerVehicle : customerVehicles) {
                    try {
                        vehicles[counter++] = VehicleDao.loadById(customerVehicle.getVehicleId());
                        request.setAttribute("vehicles", vehicles);
                    } catch (SQLException e) {
                        throw new ServletException("Error while fetching vehicle with id " + id + ". ", e);
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Error while fetching customer vehicles. ", e);
            }
            request.setAttribute("customer", customer);
            getServletContext().getRequestDispatcher("/customers/details.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching customer with id " + id + ". ", e);
        }
    }


}
