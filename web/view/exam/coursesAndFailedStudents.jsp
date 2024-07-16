<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Courses and Results</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/viewpass.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <img src="../img/logo.png" class="top-left-image" alt="Logo"/>
                <span class="navbar-text">Welcome, ${sessionScope.user.displayname} So Cute And Handsome</span>
                <div class="ml-auto">
                    <form action="${pageContext.request.contextPath}/lecturer/dashboard" method="GET" class="d-inline">
                        <button type="submit" class="btn btn-outline-primary">Home</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/logout" method="POST" class="d-inline">
                        <button type="submit" class="btn btn-outline-danger">Logout</button>
                    </form>
                </div>
            </div>
        </nav>
    </c:if>
    <div class="container mt-5 pt-5">
        <c:if test="${requestScope.courses != null}">
            <form action="${pageContext.request.contextPath}/view/pass" method="POST" class="mb-4">
                <div class="form-group">
                    <label for="course-select">Select a course:</label>
                    <select name="cid" id="course-select" class="form-control">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <option value="${c.id}">
                                ${c.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">View Rate</button>
            </form>
        </c:if>
        
        <c:if test="${requestScope.results != null}">
            <h2>Student Grade</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Year</th>
                            <th>Semester</th>
                            <th>Subject Name</th>
                            <th>Total Weighted Score</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="result" items="${results}">
                            <tr>
                                <td>${result.sid}</td>
                                <td>${result.sname}</td>
                                <td>${result.year}</td>
                                <td>${result.semester}</td>
                                <td>${result.subjectName}</td>
                                <td>${result.totalWeightedScore}</td>
                                <td>${result.status}</td>
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
