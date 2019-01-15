package pl.coderslab.servlet.customerVehicle;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.CustomerVehicleDao;
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

@WebServlet(name = "CustomersVehiclesAssignCustomerServlet", urlPatterns = "/customers_vehicles/assign_to_vehicle")
public class AssignCustomerServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");

    Vehicle vehicle = (Vehicle) request.getSession().getAttribute("vehicleInSession");
    String vehicleIdParam = request.getParameter("vehicleId");
    String customerIdParam = request.getParameter("customerId");

    CustomerVehicle customerVehicle = null;
    if (vehicleIdParam != null && !vehicleIdParam.equals("") && customerIdParam != null && !customerIdParam.equals("")) {
        customerVehicle = new CustomerVehicle(Long.valueOf(customerIdParam), Long.valueOf(vehicleIdParam));
    }
    try {
        CustomerVehicleDao.saveToDb(customerVehicle);
        response.sendRedirect("/null/vehicles/details?vehicleId=" + vehicle.getId());
    } catch (SQLException e) {
        throw new ServletException("Error while adding vehicle to customer. ", e);
    }
}


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");

    Vehicle vehicle = (Vehicle) request.getSession().getAttribute("vehicleInSession");
    request.setAttribute("vehicle", vehicle);

    try {
        Customer[] customers = CustomerDao.loadAll();
        request.setAttribute("customers", customers);
    } catch (SQLException e) {
        throw new ServletException("Error while fetching customers. ", e);
    }

    getServletContext().getRequestDispatcher("/customers/assign_to_vehicle.jsp").forward(request, response);


}


}
