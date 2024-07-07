package controller.exam;

import dal.CourseDBContext;
import dal.GradeDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Grade;
import model.User;

public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getStudent() != null) {
            CourseDBContext courseDB = new CourseDBContext();
            GradeDBContext gradeDB = new GradeDBContext();
            
            ArrayList<Course> courses = courseDB.getCoursesByStudent(user.getStudent().getId());
            for (Course course : courses) {
                course.setGrades(gradeDB.getGradesByCourseIdAndStudentId(course.getId(), user.getStudent().getId()));
            }
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("student.jsp").forward(request, response);
        } else {
            response.getWriter().println("access denied!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Student Controller";
    }
}
