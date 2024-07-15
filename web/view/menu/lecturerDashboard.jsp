<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecturer Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="../custom.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container">
                <span class="navbar-brand">Welcome, ${sessionScope.user.displayname} So Cute And Handsome (Lecturer)</span>
                <div class="ml-auto d-flex">
                    <a href="${pageContext.request.contextPath}/view/profile?username=${sessionScope.user.username}" class="btn btn-outline-info mr-2">View Profile</a>
                    <form class="form-inline" action="${pageContext.request.contextPath}/logout" method="POST">
                        <button class="btn btn-outline-success" type="submit">Logout</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container mt-5 pt-5">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title text-center">Lecturer View</h2>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item text-center"><a href="${pageContext.request.contextPath}/exam/lecturer" class="btn btn-success">View Courses</a></li>
                        <li class="list-group-item text-center"><a href="${pageContext.request.contextPath}/view/pass" class="btn btn-success">View Class</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
