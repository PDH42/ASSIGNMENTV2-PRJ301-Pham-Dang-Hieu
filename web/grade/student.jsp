<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Xem khóa học</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #ff9800;
            margin: 20px;
            font-family: Arial, sans-serif;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: auto;
        }
        h1 {
            margin-bottom: 30px;
            color: #4CAF50;
        }
        .logout-button {
            background-color: #f44336;
            border: none;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            font-size: 12px;
            margin-top: 20px;
        }
        .logout-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Khóa học của bạn</h1>
        <c:forEach items="${courses}" var="course">
            <h2>${course.name}</h2>
            <ul>
                <c:forEach items="${course.exams}" var="exam">
                    <li>${exam.name}: ${exam.score}</li>
                </c:forEach>
            </ul>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/logout" class="logout-button">ĐĂNG XUẤT</a>
    </div>
</body>
</html>
