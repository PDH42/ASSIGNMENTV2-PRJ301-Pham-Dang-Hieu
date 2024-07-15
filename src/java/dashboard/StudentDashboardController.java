package dashboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import model.User;
import controller.auth.BaseRequiredStudentAuthenticationController;

public class StudentDashboardController extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/menu/studentDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Student Dashboard Controller";
    }
}
