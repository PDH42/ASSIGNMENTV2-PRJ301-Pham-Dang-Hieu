package controller.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Lecturer;
import model.User;
import dal.UserDBContext;

public class InformatinLecturer extends BaseRequiredLecturerAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");

        UserDBContext db = new UserDBContext();
        User retrievedUser = db.getUserByLectuere(username);

        if (retrievedUser != null) {
            request.setAttribute("user", retrievedUser);
            request.getRequestDispatcher("/view/auth/userProfile.jsp").forward(request, response); // Ensure this path is correct
        } else {
            response.getWriter().println("User not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        // Implement if POST functionality is needed
    }
}