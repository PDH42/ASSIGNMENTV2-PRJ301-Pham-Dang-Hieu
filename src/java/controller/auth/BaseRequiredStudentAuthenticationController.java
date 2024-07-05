package controller.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import model.User;

public abstract class BaseRequiredStudentAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null && user.getStudent() != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            User user = (User) request.getSession().getAttribute("user");
            doGet(request, response, user, user.getStudent());
        } else {
            response.getWriter().println("Access denied!");
        }
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            User user = (User) request.getSession().getAttribute("user");
            doPost(request, response, user, user.getStudent());
        } else {
            response.getWriter().println("Access denied!");
        }
    }

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException;
}
