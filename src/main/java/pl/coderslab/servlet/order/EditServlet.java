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

@WebServlet(name = "OrdersEditServlet", urlPatterns = "/orders/edit")
public class EditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("orderId");
        String acceptanceDateParam = request.getParameter("acceptanceDate");
        String scheduledStartDateParam = request.getParameter("scheduledStartDate");
        String startDateParam = request.getParameter("startDate");
        String employeeIdParam = request.getParameter("employeeId");
        String problemDescription = request.getParameter("problemDescription");
        String repairDescription = request.getParameter("repairDescription");
        String status = request.getParameter("status");
        String vehicleIdParam = request.getParameter("vehicleId");
        String manHoursParam = request.getParameter("manHours");
        String manHourCostParam = request.getParameter("manHourCost");
        String partsCostParam = request.getParameter("partsCost");
        String costForCustomerParam = request.getParameter("costForCustomer");

        Order order = new Order();
        order.setId(Long.valueOf(idParam));
        if (acceptanceDateParam != null && !acceptanceDateParam.equals("")) {
            order.setAcceptanceDate(Date.valueOf(acceptanceDateParam));
        }
        if (scheduledStartDateParam != null && !scheduledStartDateParam.equals("")) {
            order.setScheduledStartDate(Date.valueOf(scheduledStartDateParam));
        }
        if (startDateParam != null && !startDateParam.equals("")) {
            order.setStartDate(Date.valueOf(startDateParam));
        }
        if (employeeIdParam != null && !employeeIdParam.equals("")) {
            order.setEmployeeId(Long.valueOf(employeeIdParam));
        }
        if (problemDescription != null && !problemDescription.equals("")) {
            order.setProblemDescription(problemDescription);
        }
        if (repairDescription != null && !repairDescription.equals("")) {
            order.setRepairDescription(repairDescription);
        }
        if (status != null && !status.equals("")) {
            order.setStatus(status);
        }
        if (vehicleIdParam != null && !vehicleIdParam.equals("")) {
            order.setVehicleId(Long.valueOf(vehicleIdParam));
        }
        if (manHoursParam != null && !manHoursParam.equals("")) {
            order.setManHours(Double.valueOf(manHoursParam));
        }
        if (manHourCostParam != null && !manHourCostParam.equals("")) {
            order.setManHourCost(Double.valueOf(manHourCostParam));
        }
        if (partsCostParam != null && !partsCostParam.equals("")) {
            order.setPartsCost(Double.valueOf(partsCostParam));
        }
        if (costForCustomerParam != null && !costForCustomerParam.equals("")) {
            order.setCostForCustomer(Double.valueOf(costForCustomerParam));
        }
        try {
            OrderDao.saveToDb(order);
            request.setAttribute("showInfoEdited", 1);
            request.setAttribute("orderId", order.getId());
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while editing orders. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("orderId");
        long id = 0;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
        }
        try {
            Order order = OrderDao.loadById(id);
            request.setAttribute("order", order);
            getServletContext().getRequestDispatcher("/orders/edit.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching order with id " + id + ". ", e);
        }
    }


}
