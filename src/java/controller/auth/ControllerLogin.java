package controller.auth;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class ControllerLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDBContext db = new UserDBContext();
        User user = db.getUserByUsernamePassword(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (user.getLecturer() != null) {
                response.sendRedirect("exam/lecturer");
            } else if (user.getStudent() != null) {
                response.sendRedirect("student/grades");
            } else {
                response.getWriter().println("Unknown user role!");
            }
        } else {
            request.setAttribute("message", "Login failed! Please try again.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("view/auth/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
