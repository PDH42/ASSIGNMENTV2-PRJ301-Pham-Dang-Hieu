<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: darkorange;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            min-height: 100vh;
            position: relative;
        }
        .header {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            box-sizing: border-box;
            position: fixed;
            top: 0;
            left: 0;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            z-index: 1000;
        }
        .welcome {
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }
        .logout {
            margin: 0;
        }
        .container {
            background-color: white;
            padding: 40px;
            margin: 60px 20px 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 800px;
            box-sizing: border-box;
            text-align: center;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a, input[type="submit"] {
            background-color: green;
            color: white;
            text-decoration: none;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            display: inline-block;
        }
        a:hover, input[type="submit"]:hover {
            background-color: darkgreen;
        }
    </style>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname} (Student)</div>
            <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                <input type="submit" value="Logout"/>
            </form>
        </div>
        <div class="container">
            <h2>Student Dashboard</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/student/course">View Courses</a></li>
                <li><a href="${pageContext.request.contextPath}/student/info?username=${sessionScope.user.username}">View User Profile</a></li>
            </ul>
        </div>
    </c:if>
</body>
</html>
