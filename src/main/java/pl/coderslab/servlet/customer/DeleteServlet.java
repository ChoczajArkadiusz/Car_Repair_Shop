package pl.coderslab.servlet.customer;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomersDeleteServlet", urlPatterns = "/customers/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("customerIdDelete");
        try {
            CustomerDao.delete(Long.valueOf(idParam));
            request.setAttribute("showInfoDeleted", 1);
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while deleting customers. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("customerId");
        long id = 0;
        Customer customer;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
            try {
                customer = CustomerDao.loadById(id);
                request.setAttribute("customer", customer);
            } catch (SQLException e) {
                throw new ServletException("Error while fetching customer with id " + id + ". ", e);
            }
        }
        getServletContext().getRequestDispatcher("/customers/edit.jsp").forward(request, response);
    }


}
