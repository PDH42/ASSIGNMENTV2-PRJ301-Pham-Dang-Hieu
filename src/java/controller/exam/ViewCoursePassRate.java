package controller.exam;

import controller.auth.BaseRequiredLecturerAuthenticationController;
import dal.CourseDBContext;
import dal.GradeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.Student;
import model.Lecturer;
import model.User;

public class ViewCoursePassRate extends BaseRequiredLecturerAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        CourseDBContext db = new CourseDBContext();
        ArrayList<Course> courses = db.getCoursesByTeacher(lecturer.getId());
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/viewpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));

    GradeDBContext gradeDB = new GradeDBContext();
    float passRate = gradeDB.getPassRate(cid);
    ArrayList<Student> failedStudents = gradeDB.getFailedStudents(cid);

    request.setAttribute("passRate", passRate);
    request.setAttribute("failedStudents", failedStudents);
    request.getRequestDispatcher("../view/exam/viewpass.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
