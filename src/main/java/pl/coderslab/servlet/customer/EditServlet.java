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

@WebServlet(name = "CustomersEditServlet", urlPatterns = "/customers/edit")
public class EditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("customerId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDateParam = request.getParameter("birthDate");

        Customer customer = new Customer();
        customer.setId(Long.valueOf(idParam));
        if (firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            if (birthDateParam != null) {
                customer.setBirthDate(Date.valueOf(birthDateParam));
            }
        }
        try {
            CustomerDao.saveToDb(customer);
            request.setAttribute("showInfoEdited", 1);
            request.setAttribute("customerId", customer.getId());
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while editing customers. ", e);
        }
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
            request.setAttribute("customer", customer);
            getServletContext().getRequestDispatcher("/customers/edit.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching customer with id " + id + ". ", e);
        }
    }


}
