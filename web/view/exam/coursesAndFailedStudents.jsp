<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Courses and Results</title>
    <link href="../custom.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname} So Cute And Handsome</div>
            <div class="header-buttons">
                <form action="${pageContext.request.contextPath}/lecturer/dashboard" method="GET" class="home">
                    <input type="submit" value="Home" class="home-button"/>
                </form>
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                    <input type="submit" value="Logout" class="logout-button"/>
                </form>
            </div>
        </div>
    </c:if>
    <div class="container">
        <c:if test="${requestScope.courses != null}">
            <form action="${pageContext.request.contextPath}/view/pass" method="POST">
                <label for="course-select">Select a course:</label>
                <select name="cid" id="course-select">
                    <c:forEach items="${requestScope.courses}" var="c">
                        <option value="${c.id}">
                            ${c.name}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="View Rate"/>
            </form>
        </c:if>
        
        <c:if test="${requestScope.results != null}">
            <h2>Student Results</h2>
            <table>
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Year</th>
                        <th>Semester</th>
                        <th>Subject Name</th>
                        <th>Total Weighted Score</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.sid}</td>
                            <td>${result.sname}</td>
                            <td>${result.year}</td>
                            <td>${result.semester}</td>
                            <td>${result.subjectName}</td>
                            <td>${result.totalWeightedScore}</td>
                            <td>${result.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
