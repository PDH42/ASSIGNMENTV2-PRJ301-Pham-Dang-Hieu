<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Course Pass Rate</title>
</head>
<body>
    <h1>Select a Course</h1>
    <form action="pass" method="post">
        <label for="cid">Course:</label>
        <select name="cid" id="cid">
            <c:forEach var="course" items="${courses}">
                <option value="${course.id}" <c:if test="${course.id == selectedCourseId}">selected</c:if>>${course.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="View Pass Rate">
    </form>

    <c:if test="${not empty passRate}">
        <h2>Pass Rate: ${passRate}%</h2>
        <h3>Failed Students:</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
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
    </c:if>
</body>
</html>
