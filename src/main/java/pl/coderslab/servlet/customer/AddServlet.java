package pl.coderslab.servlet.customer;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "CustomersAddServlet", urlPatterns = "/customers/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDateParam = request.getParameter("birthDate");

        Customer customer = null;
        if (firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
            if (birthDateParam == null) {
                customer = new Customer(firstName, lastName);
            } else {
                Date birthDate = Date.valueOf(birthDateParam);
                customer = new Customer(firstName, lastName, birthDate);
            }
        }
        try {
            CustomerDao.saveToDb(customer);
            request.setAttribute("showInfoAdded", 1);
            getServletContext().getRequestDispatcher("/customers/add.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while adding customers. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("/null/customers/add.jsp");
    }


}
