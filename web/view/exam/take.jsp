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
                max-width: 1000px;
                box-sizing: border-box;
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
            }
            input[type="text"] {
                width: 100%;
                padding: 5px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: green;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                font-size: 16px;
                border-radius: 5px;
            }
            input[type="submit"]:hover {
                background-color: darkgreen;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user != null}">
            <div class="header">
                <div class="welcome">Welcome, ${sessionScope.user.displayname} So Cute And Handsome</div>
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                    <input type="submit" value="Logout"/>
                </form>
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
