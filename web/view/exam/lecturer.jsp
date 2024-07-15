<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecturer Page</title>
    <link href="../lecturer.css" rel="stylesheet" type="text/css"/>
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
                    <input type="submit" value="Logout"/>
                </form>
            </div>
        </div>
    </c:if>
    <div class="container">
        <c:if test="${requestScope.exams eq null}">
            <c:if test="${requestScope.courses.size() > 0}">
                <form action="lecturer" method="POST">
                    <input type="hidden" name="lid" value="${param.lid}"/>
                    <div class="form-group">
                        <label for="course-select">Select a course:</label>
                        <select name="cid" id="course-select">
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.id}">
                                    ${c.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="submit" value="View"/>
                    </div>
                </form>
            </c:if>
        </c:if>
        <c:if test="${requestScope.exams ne null}">
            <form action="take" method="GET">
                <input type="hidden" name="cid" value="${param.cid}"/>
                <div class="form-group">
                    <c:forEach items="${requestScope.exams}" var="e">
                        <div class="checkbox-container">
                            <input type="checkbox" name="eid" value="${e.id}" /> 
                            <label>${e.assessment.name} - (${e.from}: ${e.assessment.weight}%)</label>
                        </div>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <input type="submit" value="Take"/>
                </div>
            </form>
        </c:if>
    </div>
</body>
</html>
