<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Grades</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-jcSm16ho7P5kn6Bb10d6ClYmvh11w8p8ptCSsPlOxJlE5AlD5Kk5G2sTn0Kx3jV5" crossorigin="anonymous">
    <style>
        body {
            background-color: #ff9800;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }
        .grades-table {
            margin-top: 20px;
        }
        .logout-button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="title">Your Grades</div>
        <table class="table table-striped grades-table">
            <thead>
                <tr>
                    <th>Course</th>
                    <th>Assessment</th>
                    <th>Score</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${grades}" var="g">
                    <tr>
                        <td>${g.exam.assessment.subject.name}</td>
                        <td>${g.exam.assessment.name}</td>
                        <td>${g.score}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlYQ/klO1iioCtJkjtOwueFB6LEnGIfEQMIuZ2sJ6paBIcB/MWJmI6j3nzp" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgPSI9qnDP4WvfxH2N9z84F1tz9L3zvnf9ZZTtmIHvKaF4h3F7T" crossorigin="anonymous"></script>
</body>
</html>
