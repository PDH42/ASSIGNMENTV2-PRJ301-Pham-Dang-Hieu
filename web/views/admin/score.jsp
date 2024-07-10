<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="model.StudentClass, model.Student, model.Instructor, model.Semester, model.Course, model.Major, model.Mark, java.util.List" %>
<%@ include file="/templates/admin/inc/header.jsp" %>

<!-- Main Sidebar Container -->
<%@ include file="/templates/admin/inc/menu.jsp" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Mark</h1>
                </div>
            </div>
        </div>
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <% String msg = request.getParameter("msg"); %>
            <% if (msg != null && !"".equals(msg)) { %>
                <div class="alert <%= "OK".equals(msg) ? "alert-success" : "ERROR".equals(msg) ? "alert-danger" : "" %>" role="alert">
                    <%= "OK".equals(msg) ? "Successfully..!" : "ERROR".equals(msg) ? "Fail..!" : "" %>
                </div>
            <% } %>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalThem">Add</button>
            <table class="table table-bordered" id="datatable">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Mark ID</th>
                        <th scope="col">Student ID</th>
                        <th scope="col">Student Name</th>
                        <th scope="col">Assignment</th>
                        <th scope="col">PT1</th>
                        <th scope="col">PT2</th>
                        <th scope="col">FE</th>
                        <th scope="col">Average</th>
                        <th scope="col">Instructor</th>
                        <th scope="col">Course</th>
                        <th scope="col">Semester</th>
                        <th scope="col">Class</th>
                        <th scope="col">Feature</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Mark> markList = (List<Mark>) request.getAttribute("markList");
                    if (markList != null && !markList.isEmpty()) {
                        for (Mark objMark : markList) { %>
                            <tr>
                                <th scope="row"><input type="checkbox" name="selectedMarks" value="<%= objMark.getMarkID() %>"></th>
                                <td><%= objMark.getMarkID() %></td>
                                <td><%= objMark.getStudent().getStudentID() %></td>
                                <td><%= objMark.getStudent().getFullName() %></td>
                                <td><%= objMark.getAssigment() %></td>
                                <td><%= objMark.getPt1() %></td>
                                <td><%= objMark.getPt2() %></td>
                                <td><%= objMark.getFe() %></td>
                                <td><%= String.format("%.1f", objMark.getAverage()) %></td>
                                <td><%= objMark.getInstructor().getInstructorName() %></td>
                                <td><%= objMark.getCourse().getCourseID() %></td>
                                <td><%= objMark.getSemester().getSemesterID() %></td>
                                <td><%= objMark.getStudentclass().getClassID() %></td>
                                <td>
                                    <button type="button" class="btn btn-danger" onclick="confirmAndDelete('<%= request.getContextPath() %>/admin/delete?mark=<%= objMark.getMarkID() %>')">Delete</button>
                                    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#modalSua">Edit</button>
                                </td>
                            </tr>
                        <% } 
                    } %>
                </tbody>
            </table>
        </div>
    </section>
    <!-- /.content -->

    <!-- Add Mark Modal -->
    <div class="modal fade" id="modalThem" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Mark</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="<%= request.getContextPath() %>/admin/mark" method="post">
                        <div class="form-group row">
                            <label for="markID" class="col-sm-3">Mark ID</label>
                            <input type="text" class="form-control col-sm-8" id="markID" name="markID">
                        </div>
                        <div class="form-group row">
                            <label for="studentID" class="col-sm-3">Student ID</label>
                            <select class="form-control col-sm-8" id="studentID" name="studentID">
                                <% 
                                List<Student> studentList = (List<Student>) request.getAttribute("studentList");
                                if (studentList != null && !studentList.isEmpty()) {
                                    for (Student objStudent : studentList) { %>
                                        <option value="<%= objStudent.getStudentID() %>"><%= objStudent.getStudentID() %></option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="assignment" class="col-sm-3">Assignment</label>
                            <input type="text" class="form-control col-sm-8" id="assignment" name="assignment">
                        </div>
                        <div class="form-group row">
                            <label for="pt1" class="col-sm-3">PT1</label>
                            <input type="text" class="form-control col-sm-8" id="pt1" name="pt1">
                        </div>
                        <div class="form-group row">
                            <label for="pt2" class="col-sm-3">PT2</label>
                            <input type="text" class="form-control col-sm-8" id="pt2" name="pt2">
                        </div>
                        <div class="form-group row">
                            <label for="fe" class="col-sm-3">FE</label>
                            <input type="text" class="form-control col-sm-8" id="fe" name="fe">
                        </div>
                        <div class="form-group row">
                            <label for="average" class="col-sm-3">Average</label>
                            <input type="text" class="form-control col-sm-8" id="average" name="average">
                        </div>
                        <div class="form-group row">
                            <label for="instructorID" class="col-sm-3">Instructor</label>
                            <select class="form-control col-sm-8" id="instructorID" name="instructorID">
                                <% 
                                List<Instructor> instructorList = (List<Instructor>) request.getAttribute("instructorList");
                                if (instructorList != null && !instructorList.isEmpty()) {
                                    for (Instructor objInstructor : instructorList) { %>
                                        <option value="<%= objInstructor.getInstructorID() %>"><%= objInstructor.getInstructorName() %></option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="courseID" class="col-sm-3">Course</label>
                            <select class="form-control col-sm-8" id="courseID" name="courseID">
                                <% 
                                List<Course> courseList = (List<Course>) request.getAttribute("courseList");
                                if (courseList != null && !courseList.isEmpty()) {
                                    for (Course objCourse : courseList) { %>
                                        <option value="<%= objCourse.getCourseID() %>"><%= objCourse.getCourseID() %></option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="classID" class="col-sm-3">Class</label>
                            <select class="form-control col-sm-8" id="classID" name="classID">
                                <% 
                                List<StudentClass> studentClassList = (List<StudentClass>) request.getAttribute("studentClassList");
                                if (studentClassList != null && !studentClassList.isEmpty()) {
                                    for (StudentClass objStudentClass : studentClassList) { %>
                                        <option value="<%= objStudentClass.getClassID() %>"><%= objStudentClass.getClassID() %></option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="semesterID" class="col-sm-3">Semester</label>
                            <select class="form-control col-sm-8" id="semesterID" name="semesterID">
                                <% 
                                List<Semester> semesterList = (List<Semester>) request.getAttribute("semesterList");
                                if (semesterList != null && !semesterList.isEmpty()) {
                                    for (Semester objSemester : semesterList) { %>
                                        <option value="<%= objSemester.getSemesterID() %>"><%= objSemester.getSemesterID() %></option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Exit</button>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>        
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Edit Mark Modal -->
    <div class="modal fade" id="modalSua" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Mark</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Edit mark form goes here -->
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function confirmAndDelete(deleteUrl) {
        if (confirm("Are you sure you want to delete this?")) {
            window.location.href = deleteUrl;
        }
    }
</script>
<!-- /.content-wrapper -->

<%@ include file="/templates/admin/inc/footer.jsp" %>
