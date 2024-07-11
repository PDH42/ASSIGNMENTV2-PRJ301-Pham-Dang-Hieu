package controller.exam;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.CourseDBContext;
import dal.ExamDBContext;
import dal.GradeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.Exam;
import model.Grade;
import model.Student;
import model.User;

public class ViewCourseStudent extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        CourseDBContext db = new CourseDBContext();
        int sid = student.getId();
        ArrayList<Course> courses = db.getCoursesByStudent(sid);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        int sid = student.getId();

        ExamDBContext examDB = new ExamDBContext();
        GradeDBContext gradeDB = new GradeDBContext();
        ArrayList<Exam> exams = examDB.getExamsByCourse(cid);

        for (Exam exam : exams) {
            Grade grade = gradeDB.getGradeByExamAndStudent(exam.getId(), sid);
            exam.setGrade(grade);
        }
        boolean passed = gradeDB.hasStudentPassedCourse(sid, cid);
        float totalWeightedScore = gradeDB.getTotalWeightedScore(sid, cid);

        request.setAttribute("exams", exams);
        request.setAttribute("totalWeightedScore", totalWeightedScore);
        request.setAttribute("passed", passed ? "Pass" : "Not Pass");
        request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
