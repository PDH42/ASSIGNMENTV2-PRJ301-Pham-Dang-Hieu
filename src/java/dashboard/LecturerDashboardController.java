package dashboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class LecturerDashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getLecturer() != null) {
            request.getRequestDispatcher("/view/menu/lecturerDashboard.jsp").forward(request, response);
        } else {
            response.getWriter().println("Access denied!");
        }
    }
}
