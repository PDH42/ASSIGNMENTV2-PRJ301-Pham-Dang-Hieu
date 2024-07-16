<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Courses and Exams</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/viewstudent.css" rel="stylesheet" type="text/css"/>
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
        <c:if test="${requestScope.exams eq null}">
            <c:if test="${requestScope.courses.size() > 0}">
                <form action="course" method="POST" class="mb-4">
                    <input type="hidden" name="sid" value="${param.sid}"/>
                    <div class="form-group">
                        <label for="courseSelect">Course:</label>
                        <select name="cid" id="courseSelect" class="form-control">
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">View Exams</button>
                </form>
            </c:if>
        </c:if>

        <c:if test="${requestScope.exams ne null}">
            <h2>Exam Scores</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Exam Name</th>
                            <th>Date</th>
                            <th>Weight</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.exams}" var="e">
                            <tr>
                                <td>${e.assessment.name}</td>
                                <td>${e.from}</td>
                                <td>${e.assessment.weight}%</td>
                                <td>${e.grade.score}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <h3>Total Weighted Score: ${requestScope.totalWeightedScore}</h3>
            <h3>Status: ${requestScope.passed}</h3>
        </c:if>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
