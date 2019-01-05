package pl.coderslab.servlet.employee;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EmployeesDeleteServlet", urlPatterns = "/employees/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("employeeIdDelete");
        try {
            EmployeeDao.delete(Long.valueOf(idParam));
            request.setAttribute("showInfoDeleted", 1);
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while deleting employees. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("employeeId");
        long id = 0;
        Employee employee;
        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
            try {
                employee = EmployeeDao.loadById(id);
                request.setAttribute("employee", employee);
            } catch (SQLException e) {
                throw new ServletException("Error while fetching employee with id " + id + ". ", e);
            }
        }
        getServletContext().getRequestDispatcher("/employees/edit.jsp").forward(request, response);
    }


}
