package controller;

import dal.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
        rd.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        AccountDAO accountDAO = new AccountDAO();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        //Kiểm tra thông tin đăng nhập
        Account userInfor = accountDAO.findUsernameANDPass(user, pass);
        if (userInfor != null) {
            //đăng nhập đúng
            //Lưu thông tin đăng nhập(session)
            Account account = accountDAO.viewAll(user);
            if (account != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfor", account);
                //Chuyển hướng đến trang index admin
                if ((account.getRole().getRoleName()).equals("Admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    response.sendRedirect(request.getContextPath() + "/user");
                }
            }
        } else {
            // đăng nhập sai
            response.sendRedirect(request.getContextPath() + "/auth/login?msg=ERROR");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
