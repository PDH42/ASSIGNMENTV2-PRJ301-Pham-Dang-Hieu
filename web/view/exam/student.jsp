<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Courses and Exams</title>
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
            margin: 80px 20px 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 800px;
            box-sizing: border-box;
            text-align: center;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
            font-size: 18px;
        }
        select, input[type="submit"] {
            padding: 10px;
            margin: 10px 0;
            width: 100%;
            max-width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: green;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: darkgreen;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f9f9f9;
        }
        h3 {
            margin-top: 20px;
            color: #333;
        }
    </style>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname}</div>
            <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                <input type="submit" value="Logout"/>
            </form>
        </div>
    </c:if>
    <div class="container">
        <c:if test="${requestScope.exams eq null}">
            <c:if test="${requestScope.courses.size() > 0}">
                <form action="course" method="POST">
                    <input type="hidden" name="sid" value="${param.sid}"/>
                    <label for="courseSelect">Course:</label>
                    <select name="cid" id="courseSelect">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="View Exams"/>
                </form>
            </c:if>
        </c:if>

        <c:if test="${requestScope.exams ne null}">
            <h2>Exam Scores</h2>
            <table>
                <tr>
                    <th>Exam Name</th>
                    <th>Date</th>
                    <th>Weight</th>
                    <th>Score</th>
                </tr>
                <c:forEach items="${requestScope.exams}" var="e">
                    <tr>
                        <td>${e.assessment.name}</td>
                        <td>${e.from}</td>
                        <td>${e.assessment.weight}%</td>
                        <td>${e.grade.score}</td>
                    </tr>
                </c:forEach>
            </table>
            <h3>Total Weighted Score: ${requestScope.totalWeightedScore}</h3>
            <h3>Status: ${requestScope.passed}</h3>
        </c:if>
    </div>
</body>
</html>
