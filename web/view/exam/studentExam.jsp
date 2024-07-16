<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam Schedule</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/exam.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${sessionScope.user != null}">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <img src="../img/logo.png" class="top-left-image" alt="Logo"/>
            <span class="navbar-text">Welcome, ${sessionScope.user.displayname}</span>
            <div class="ml-auto">
                <a href="${pageContext.request.contextPath}/student/dashboard" class="btn btn-outline-primary mr-2">Home</a>
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="d-inline">
                    <button type="submit" class="btn btn-outline-danger">Logout</button>
                </form>
            </div>
        </div>
    </nav>
</c:if>

<div class="container mt-5 pt-5">
    <h1>Exam Schedule</h1>
    <form method="post" action="exam" class="mb-4">
        <div class="form-group">
            <label for="cid">Select Course:</label>
            <select name="cid" id="cid" class="form-control">
                <c:forEach var="course" items="${courses}">
                    <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Show Exam</button>
    </form>

    <c:if test="${not empty schedules}">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Course Name</th>
                        <th>Subject Name</th>
                        <th>Assessment Name</th>
                        <th>Exam Date</th>
                        <th>Exam Duration</th>
                        <th>Exam Room</th>
                        <th>Score Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="schedule" items="${schedules}">
                        <tr>
                            <td>${schedule.courseName}</td>
                            <td>${schedule.subjectName}</td>
                            <td>${schedule.assessmentName}</td>
                            <td>${schedule.examDate}</td>
                            <td>${schedule.examDuration}</td>
                            <td>${schedule.examRoom}</td>
                            <td>${schedule.scoreDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
