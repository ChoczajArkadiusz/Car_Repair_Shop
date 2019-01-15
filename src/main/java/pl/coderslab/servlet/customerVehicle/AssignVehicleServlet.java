package pl.coderslab.servlet.customerVehicle;

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

    @WebServlet(name = "CustomersVehiclesAssignVehicleServlet", urlPatterns = "/customers_vehicles/assign_to_customer")
public class AssignVehicleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        Customer customer = (Customer) request.getSession().getAttribute("customerInSession");
        String vehicleIdParam = request.getParameter("vehicleId");
        String customerIdParam = request.getParameter("customerId");

        CustomerVehicle customerVehicle = null;
        if (vehicleIdParam != null && !vehicleIdParam.equals("") && customerIdParam != null && !customerIdParam.equals("")) {
            customerVehicle = new CustomerVehicle(Long.valueOf(customerIdParam), Long.valueOf(vehicleIdParam));
        }
        try {
            CustomerVehicleDao.saveToDb(customerVehicle);
            response.sendRedirect("/null/customers/details?customerId=" + customer.getId());
        } catch (SQLException e) {
            throw new ServletException("Error while adding customers vechicle. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        Customer customer = (Customer) request.getSession().getAttribute("customerInSession");
        request.setAttribute("customer", customer);

        try {
            Vehicle[] vehicles = VehicleDao.loadAll();
            request.setAttribute("vehicles", vehicles);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching vehicles. ", e);
        }

        getServletContext().getRequestDispatcher("/vehicles/assign_to_customer.jsp").forward(request, response);


    }


}
