package pl.coderslab.servlet.employee;

import pl.coderslab.dao.DbInit;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EmployeesShowAllServlet", urlPatterns = "/employees/show_all")
public class ShowAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
//            DbInit.createTableEmployees();
            Employee[] employees = EmployeeDao.loadAll();
            request.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/employees/show_all.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching employees. ", e);
        }
    }


}
