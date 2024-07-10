/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller2;

import dal.CourseDAO;
import dal.InstructorDAO;
import dal.MarkDAO;
import dal.SemesterDAO;
import dal.StudentClassDAO;
import dal.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Mark;
import model.Student;
import model.StudentClass;
import model.Instructor;
import model.Course;
import model.Semester;
import model.Major;

/**
 *
 * @author Acer
 */
public class UserMServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        MarkDAO markDAO = new MarkDAO();
        List<Mark> markist = markDAO.getAll();
        request.setAttribute("markList", markist);

        StudentDAO studentDAO = new StudentDAO();
        List<Student> studentList = studentDAO.getAll();
        request.setAttribute("studentList", studentList);

        InstructorDAO instructorDAO = new InstructorDAO();
        List<Instructor> instructorList = instructorDAO.getAll();
        request.setAttribute("instructorList", instructorList);

        StudentClassDAO studentClassDAO = new StudentClassDAO();
        List<StudentClass> studentClassList = studentClassDAO.getAll();
        request.setAttribute("studentClassList", studentClassList);

        SemesterDAO semesterDAO = new SemesterDAO();
        List<Semester> semesterList = semesterDAO.getAll();
        request.setAttribute("semesterList", semesterList);

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courseList = courseDAO.getAll();
        request.setAttribute("courseList", courseList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/user/score.jsp");
        rd.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        MarkDAO markDAO = new MarkDAO();

        String instructorID = request.getParameter("instructorID");
        String studentID = request.getParameter("studentID");
//        String studentName = request.getParameter("studentName");
        String classID = request.getParameter("classID");
        String courseID = request.getParameter("courseID");
        String semesterID = request.getParameter("semesterID");
        int markID = 0;
        double assignment = 0;
        double pt1 = 0;
        double pt2 = 0;
        double fe = 0;
        double average = 0;
        try {
            markID = Integer.parseInt(request.getParameter("markID"));
            assignment = Double.parseDouble(request.getParameter("assignment"));
            pt1 = Double.parseDouble(request.getParameter("pt1"));
            pt2 = Double.parseDouble(request.getParameter("pt2"));
            fe = Double.parseDouble(request.getParameter("fe"));
            average = Double.parseDouble(request.getParameter("average"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Mark objMark = new Mark(markID, assignment, pt1, pt2, fe, average,
                new Instructor(instructorID, null, null, 0),
                new Student(studentID, null, null, null, 0, null, null,
                        new StudentClass(classID, null
                        )),
                new Course(courseID, null),
                new Semester(semesterID, null),
                new StudentClass(classID,
                        new Major(null, null)));
        int add = markDAO.addMark(objMark);
        if (add > 0) {
            response.sendRedirect(request.getContextPath() + "/user/mark?msg=OK");
            return;
        } else {
            response.sendRedirect(request.getContextPath() + "/user/mark?msg=ERROR");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
