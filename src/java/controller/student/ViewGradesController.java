package controller.student;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.GradeDBContext;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Grade;
import model.Student;
import model.User;

public class ViewGradesController extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        GradeDBContext db = new GradeDBContext();
        int sid = student.getId();
        ArrayList<Grade> grades = db.getGradesByStudentId(sid);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("../view/student/grades.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        doGet(request, response, user, student);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to view student grades";
    }
}
