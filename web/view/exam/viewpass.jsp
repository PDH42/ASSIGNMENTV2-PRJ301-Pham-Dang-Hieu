<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Teacher View - Pass Rate</title>
</head>
<body>
    <h1>Welcome, Teacher</h1>
    <c:if test="${requestScope.failedStudents eq null}">
        <c:if test="${requestScope.courses.size() > 0}">
            <form action="pass" method="POST">
                <label for="courseSelect">Course:</label>
                <select name="cid" id="courseSelect">
                    <c:forEach items="${requestScope.courses}" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="View Pass Rate"/>
            </form>
        </c:if>
    </c:if>
    
    <c:if test="${requestScope.failedStudents ne null}">
        <h2>Pass Rate: ${requestScope.passRate}%</h2>
        <h3>Students Who Did Not Pass</h3>
        <table border="1">
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
            </tr>
            <c:forEach items="${requestScope.failedStudents}" var="student">
                <tr>
                    
                    <td>${student.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
