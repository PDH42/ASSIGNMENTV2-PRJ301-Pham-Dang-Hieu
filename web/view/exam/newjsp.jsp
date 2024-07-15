<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Pass Rate</title>
</head>
<body>
    <h1>Course Pass Rate</h1>

    <h2>Pass Rate: ${passRate}%</h2>

    <h3>Failed Students:</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Average Score</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${failedStudents}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="view/pass">Back to Course Selection</a>
</body>
</html>
