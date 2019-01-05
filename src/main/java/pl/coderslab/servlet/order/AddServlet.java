package pl.coderslab.servlet.order;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "OrdersAddServlet", urlPatterns = "/orders/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String acceptanceDateParam = request.getParameter("acceptanceDate");
        String scheduledStartDateParam = request.getParameter("scheduledStartDate");
//        String startDateParam = request.getParameter("startDate");
//        String employeeIdParam = request.getParameter("employeeId");
        String problemDescription = request.getParameter("problemDescription");
//        String repairDescription = request.getParameter("repairDescription");
//        String status = request.getParameter("status");
        String vehicleIdParam = request.getParameter("vehicleId");
//        String manHoursParam = request.getParameter("manHours");
//        String manHourCostParam = request.getParameter("manHourCost");
//        String partsCostParam = request.getParameter("partsCost");
//        String costForCustomerParam = request.getParameter("costForCustomer");
        long employeeId = 1L;
        Double manHours = 0.0;
        Double manHourCost = 0.0;
        Double partsCost = 0.0;
        Double costForCustomer = 0.0;

        Order order = new Order();
        if (acceptanceDateParam != null && !acceptanceDateParam.equals("") &&
                scheduledStartDateParam != null && !scheduledStartDateParam.equals("") &&
                problemDescription != null && !problemDescription.equals("")) {
            order.setAcceptanceDate(Date.valueOf(acceptanceDateParam));
            order.setScheduledStartDate(Date.valueOf(scheduledStartDateParam));
            order.setStartDate(Date.valueOf("1970-01-01"));
            order.setEmployeeId(employeeId);
            order.setProblemDescription(problemDescription);
            order.setRepairDescription("");
            order.setStatus("accepted");
            order.setVehicleId(Long.valueOf(vehicleIdParam));
            order.setManHours(manHours);
            order.setManHourCost(manHourCost);
            order.setPartsCost(partsCost);
            order.setCostForCustomer(costForCustomer);
        }
        try {
            OrderDao.saveToDb(order);
            request.setAttribute("showInfoAdded", 1);
            getServletContext().getRequestDispatcher("/orders/add.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while adding orders. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("/null/orders/add.jsp");
    }


}
