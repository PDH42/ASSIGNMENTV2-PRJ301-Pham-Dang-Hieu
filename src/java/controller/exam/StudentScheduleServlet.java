package controller.exam;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.ScheduleDBContext;
import model.Schedule;
import model.Student;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentScheduleServlet extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        ScheduleDBContext db = new ScheduleDBContext();
        int sid = student.getId();
        ArrayList<Schedule> schedules = db.listByStudentId(sid);
        request.setAttribute("scheduleList", schedules);
        request.getRequestDispatcher("../view/exam/lichhoc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        // Typically, you would have form handling here.
        doGet(request, response, user, student);
    }
}
