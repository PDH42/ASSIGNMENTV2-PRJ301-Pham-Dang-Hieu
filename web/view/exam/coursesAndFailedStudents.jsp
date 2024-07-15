<%-- 
    Document   : coursesAndFailedStudents
    Created on : Jun 19, 2024
    Author     : X1 Nano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Courses and Failed Students</title>
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
            <c:if test="${requestScope.failedStudents eq null}">
                <c:if test="${requestScope.courses.size() > 0}">
                    <form action="pass" method="POST">
                        <label for="course-select">Select a course:</label>
                        <select name="cid" id="course-select">
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.id}">
                                    ${c.name}
                                </option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="View Failed Students"/>
                    </form>
                </c:if>
            </c:if>

            <c:if test="${requestScope.failedStudents ne null}">
                <h2>Students Who Did Not Pass</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${requestScope.failedStudents}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
