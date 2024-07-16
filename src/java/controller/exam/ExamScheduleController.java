package controller.exam;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.CourseDBContext;
import dal.ExamDBContext;
import model.Course;
import model.ExamSchedule;
import model.Student;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ExamScheduleController extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        CourseDBContext db = new CourseDBContext();
        int sid = student.getId();
        ArrayList<Course> courses = db.getCoursesByStudent(sid);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/studentExam.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        int sid = student.getId();
        int cid = Integer.parseInt(request.getParameter("cid"));
        
        CourseDBContext courseDb = new CourseDBContext();
        ExamDBContext examDb = new ExamDBContext();
        
        ArrayList<ExamSchedule> schedules = examDb.getExamSchedule(sid, cid);
        ArrayList<Course> courses = courseDb.getCoursesByStudent(sid);

        request.setAttribute("schedules", schedules);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/studentExam.jsp").forward(request, response);
    }
}
