package dashboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Lecturer;
import model.User;
import controller.auth.BaseRequiredLecturerAuthenticationController;

public class LecturerDashboardController extends BaseRequiredLecturerAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/menu/lecturerDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Lecturer Dashboard Controller";
    }
}
