package pl.coderslab.servlet.order;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrdersDeleteServlet", urlPatterns = "/orders/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("orderIdDelete");
        try {
            OrderDao.delete(Long.valueOf(idParam));
            request.setAttribute("showInfoDeleted", 1);
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while deleting orders. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("orderId");
        long id = 0;
        Order order;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
            try {
                order = OrderDao.loadById(id);
                request.setAttribute("order", order);
            } catch (SQLException e) {
                throw new ServletException("Error while fetching order with id " + id + ". ", e);
            }
        }
        getServletContext().getRequestDispatcher("/orders/edit.jsp").forward(request, response);
    }


}
