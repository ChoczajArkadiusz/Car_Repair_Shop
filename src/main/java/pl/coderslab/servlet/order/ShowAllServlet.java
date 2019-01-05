package pl.coderslab.servlet.order;

import pl.coderslab.dao.DbInit;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrdersShowAllServlet", urlPatterns = "/orders/show_all")
public class ShowAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
//            DbInit.createTableOrders();
            Order[] orders = OrderDao.loadAll();
            request.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/orders/show_all.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching orders. ", e);
        }
    }


}
