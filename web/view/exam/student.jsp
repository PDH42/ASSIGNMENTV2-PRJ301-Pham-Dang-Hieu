<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Courses and Exams</title>
    <link href="../viewstudent.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname}</div>
            <div class="header-buttons">
                <form action="${pageContext.request.contextPath}/student/dashboard" method="GET" class="home">
                    <input type="submit" value="Home" class="home-button"/>
                </form>
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                    <input type="submit" value="Logout" class="logout-button"/>
                </form>
            </div>
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
