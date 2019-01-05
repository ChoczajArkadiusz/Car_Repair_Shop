package pl.coderslab.servlet.customer;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.DbInit;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomersShowAllServlet", urlPatterns = "/customers/show_all")
public class ShowAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            Customer[] customers = CustomerDao.loadAll();
            request.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/customers/show_all.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching customers. ", e);
        }
    }


}
