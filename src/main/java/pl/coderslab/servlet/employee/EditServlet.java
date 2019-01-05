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

@WebServlet(name = "EmployeesEditServlet", urlPatterns = "/employees/edit")
public class EditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("employeeId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        String manHourCostParam = request.getParameter("manHourCost");
        Double manHourCost = 0.0;

        Employee employee = new Employee();
        employee.setId(Long.valueOf(idParam));
        if (firstName != null && !firstName.equals("") &&
                lastName != null && !lastName.equals("") &&
                address != null && !address.equals("") &&
                phone != null && !phone.equals("")) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setAddress(address);
            employee.setPhone(phone);
            if (note == null) {
                note = "";
            }
            employee.setNote(note);
            if (manHourCostParam != null && !manHourCostParam.equals("")) {
                manHourCost = Double.valueOf(manHourCostParam);
            }
            employee.setManHourCost(manHourCost);
        }

        try {
            EmployeeDao.saveToDb(employee);
            request.setAttribute("showInfoEdited", 1);
            request.setAttribute("employeeId", employee.getId());
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while editing employees. ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("employeeId");
        long id = 0;

        if (idParam != null && !idParam.equals("")) {
            id = Long.valueOf(idParam);
        }

        try {
            Employee employee = EmployeeDao.loadById(id);
            request.setAttribute("employee", employee);
            getServletContext().getRequestDispatcher("/employees/edit.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error while fetching employee with id " + id + ". ", e);
        }
    }


}
