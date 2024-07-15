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
import model.Lecturer;
import model.Student;
import model.User;

public class ViewCoursePassRate extends BaseRequiredLecturerAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        CourseDBContext db = new CourseDBContext();
        int lid = lecturer.getId();
        ArrayList<Course> courses = db.getCoursesByLecturer(lid);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/viewpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        int lid = lecturer.getId();

        GradeDBContext gradeDB = new GradeDBContext();
        ArrayList<Student> failedStudents = gradeDB.getFailedStudentsByCourseAndLecturer(cid, lid);
        float passRate = gradeDB.getPassRateByCourseAndLecturer(cid, lid);

        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getCoursesByLecturer(lid);

        request.setAttribute("courses", courses);
        request.setAttribute("selectedCourseId", cid);
        request.setAttribute("failedStudents", failedStudents);
        request.setAttribute("passRate", passRate);
        request.getRequestDispatcher("../view/exam/viewpass.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
