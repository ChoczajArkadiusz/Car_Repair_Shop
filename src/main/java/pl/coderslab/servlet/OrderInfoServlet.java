package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Order;
import pl.coderslab.model.OrderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderInfoServlet", urlPatterns = "/orders_info")
public class OrderInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            Order[] orders = OrderDao.loadAllInRepair();
            request.setAttribute("orders", orders);
            List<OrderInfo> ordersInfosList = new ArrayList<>();

            for (int i = 0; i < orders.length; i++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.order = orders[i];
                try {
                    orderInfo.employee = EmployeeDao.loadById(orders[i].getEmployeeId());
                } catch (SQLException e) {
                    throw new ServletException("Error while fetching employee with id " + orders[i].getEmployeeId() + ". ", e);
                }
                try {
                    orderInfo.vehicle = VehicleDao.loadById(orders[i].getVehicleId());
                } catch (SQLException e) {
                    throw new ServletException("Error while fetching vehicle with id " + orders[i].getVehicleId() + ". ", e);
                }
                ordersInfosList.add(orderInfo);
            }
            OrderInfo[] ordersInfos = new OrderInfo[ordersInfosList.size()];
            ordersInfos = ordersInfosList.toArray(ordersInfos);
            request.setAttribute("ordersInfos", ordersInfos);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching orders. ", e);
        }
    }


}
