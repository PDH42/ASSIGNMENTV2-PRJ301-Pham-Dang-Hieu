<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link href="../student.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname} (Student)</div>
            <div class="actions">
                <a href="${pageContext.request.contextPath}/student/info?username=${sessionScope.user.username}" class="btn btn-outline-info">View User Profile</a>
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                    <input type="submit" value="Logout"/>
                </form>
            </div>
        </div>
        <div class="container">
            <h2>Student Dashboard</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/student/course">View Courses</a></li>
            </ul>
        </div>
    </c:if>
</body>
</html>
