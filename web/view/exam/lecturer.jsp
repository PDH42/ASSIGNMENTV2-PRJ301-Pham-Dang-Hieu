<%-- 
    Document   : lecturer
    Created on : Jun 19, 2024, 4:49:40 PM
    Author     : X1 Nano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lecturer Page</title>
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
            .form-group {
                width: 100%;
                max-width: 600px;
                margin-bottom: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-left: auto;
                margin-right: auto;
            }
            label {
                margin-bottom: 5px;
                font-weight: bold;
                font-size: 18px;
            }
            select, input[type="submit"], input[type="checkbox"] {
                width: 100%;
                padding: 15px;
                margin: 5px 0;
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
            .checkbox-container {
                display: flex;
                align-items: center;
                width: 100%;
            }
            .checkbox-container input[type="checkbox"] {
                width: auto;
                margin-right: 10px;
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
