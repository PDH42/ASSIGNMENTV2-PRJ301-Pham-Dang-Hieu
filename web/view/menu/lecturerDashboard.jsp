<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecturer Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/viewmenulecturer.css" rel="stylesheet">
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <span class="navbar-text">Welcome, ${sessionScope.user.displayname} So Cute And Handsome (Lecturer)</span>
                <div class="ml-auto">
                    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/view/profile?username=${sessionScope.user.username}">View Profile</a>
                    <form class="d-inline" action="${pageContext.request.contextPath}/logout" method="POST">
                        <button type="submit" class="btn btn-outline-danger">Logout</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <h2>Lecturer View</h2>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/exam/lecturer">View Courses</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/view/pass">View Class</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
