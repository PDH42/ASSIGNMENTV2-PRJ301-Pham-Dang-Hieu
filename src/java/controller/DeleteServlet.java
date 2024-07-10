package controller;

import dal.MarkDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mark_raw = request.getParameter("mark");
        if (mark_raw != null) {
            int mark = Integer.parseInt(mark_raw);
            MarkDAO markDAO = new MarkDAO();
            markDAO.deleteMark(mark);
            response.sendRedirect(request.getContextPath() + "/admin/mark");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
