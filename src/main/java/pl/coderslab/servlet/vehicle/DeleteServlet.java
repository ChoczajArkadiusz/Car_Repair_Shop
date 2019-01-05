package pl.coderslab.servlet.vehicle;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VehiclesDeleteServlet", urlPatterns = "/vehicles/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("vehicleIdDelete");

        try {
            VehicleDao.delete(Long.valueOf(idParam));
            request.setAttribute("showInfoDeleted", 1);
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while deleting vehicles. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("vehicleId");
        long id = 0;

        Vehicle vehicle;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
            try {
                vehicle = VehicleDao.loadById(id);
                request.setAttribute("vehicle", vehicle);
            } catch (SQLException e) {
                throw new ServletException("Error while fetching vehicle with id " + id + ". ", e);
            }
        }
        getServletContext().getRequestDispatcher("/vehicles/edit.jsp").forward(request, response);
    }


}
