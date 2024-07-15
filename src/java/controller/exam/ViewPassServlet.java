package controller.exam;

import dal.CourseDBContext;
import dal.GradeDBContext;
import model.Course;
import model.Lecturer;
import model.StudentResult;
import model.User;
import controller.auth.BaseRequiredLecturerAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ViewPassServlet extends BaseRequiredLecturerAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        // Fetch all courses taught by this lecturer
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getCoursesByLecturer(lecturer.getId());
        request.setAttribute("courses", courses);

        // Forward to JSP for course selection
        request.getRequestDispatcher("/view/exam/coursesAndFailedStudents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("cid"));

        GradeDBContext dao = new GradeDBContext();
        ArrayList<StudentResult> results = dao.getStudentResults(lecturer.getId(), courseId);

        // Fetch all courses again to allow re-selection
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getCoursesByLecturer(lecturer.getId());

        request.setAttribute("courses", courses);
        request.setAttribute("results", results);
        request.getRequestDispatcher("/view/exam/coursesAndFailedStudents.jsp").forward(request, response);
    }
}
