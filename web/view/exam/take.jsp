<%-- 
    Document   : take
    Created on : Jun 24, 2024, 2:01:09 PM
    Author     : sonnt-local
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Pass Rate</title>
    <link href="../take.css" rel="stylesheet" type="text/css"/>
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
        <form action="take" method="POST">
            <table>
                <thead>
                    <tr>
                        <th>Student Name</th>
                        <c:forEach items="${requestScope.exams}" var="e">
                            <th>${e.assessment.name} (${e.assessment.weight})<br/>${e.from}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.students}" var="s">
                        <tr>
                            <td>${s.name}</td>
                            <c:forEach items="${requestScope.exams}" var="e">
                                <td>
                                    <input type="text" name="score${s.id}_${e.id}"
                                           <c:forEach items="${requestScope.grades}" var="g">
                                               <c:if test="${e.id eq g.exam.id and s.id eq g.student.id}">
                                                   value="${g.score}"
                                               </c:if>
                                           </c:forEach>
                                           />
                                    <input type="hidden" name="gradeid" value="${s.id}_${e.id}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="hidden" name="cid" value="${param.cid}" />
            <input type="submit" value="Save"/>
        </form>
    </div>
</body>
</html>
