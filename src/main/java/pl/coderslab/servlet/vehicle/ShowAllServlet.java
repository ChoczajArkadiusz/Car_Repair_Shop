package pl.coderslab.servlet.vehicle;

import pl.coderslab.dao.DbInit;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VehiclesShowAllServlet", urlPatterns = "/vehicles/show_all")
public class ShowAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
//            DbInit.createTableVehicles();
//            DbInit.createTableCustomersVehicles();
            Vehicle[] vehicles = VehicleDao.loadAll();
            request.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/vehicles/show_all.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching vehicles. ", e);
        }
    }


}
