package controller.exam;

import dal.CourseDBContext;
import dal.GradeDBContext;
import model.Course;
import model.Lecturer;
import model.Student;
import model.User;
import controller.auth.BaseRequiredLecturerAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

        // Forward to JSP for course selection and displaying failed students
        request.getRequestDispatcher("/view/exam/coursesAndFailedStudents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Lecturer lecturer)
            throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));

        // Fetch failed students
        int lid = lecturer.getId();
        GradeDBContext gradeDB = new GradeDBContext();
        ArrayList<Student> failedStudents = gradeDB.getFailedStudentsByCourseAndLecturer(cid, lid);

        // Fetch all courses taught by this lecturer
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getCoursesByLecturer(lecturer.getId());

        // Set attributes and forward to JSP for display
        request.setAttribute("courses", courses);
        request.setAttribute("failedStudents", failedStudents);
        request.setAttribute("selectedCourseId", cid);
        request.getRequestDispatcher("/view/exam/coursesAndFailedStudents.jsp").forward(request, response);
    }
}
