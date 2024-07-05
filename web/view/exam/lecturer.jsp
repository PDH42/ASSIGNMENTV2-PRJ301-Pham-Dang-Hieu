<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>VIEW COURSE</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-jcSm16ho7P5kn6Bb10d6ClYmvh11w8p8ptCSsPlOxJlE5AlD5Kk5G2sTn0Kx3jV5" crossorigin="anonymous">
    <style>
        body {
            background-color: #ff9800;
            margin: 20px;
            font-family: Arial, sans-serif;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: auto;
        }
        h1 {
            margin-bottom: 30px;
            color: #4CAF50;
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary {
            background-color: #4CAF50;
            border-color: #4CAF50;
        }
        .btn-primary:hover {
            background-color: #45a049;
            border-color: #45a049;
        }
        .btn-success {
            background-color: #4CAF50;
            border-color: #4CAF50;
        }
        .btn-success:hover {
            background-color: #45a049;
            border-color: #45a049;
        }
        .logout-button {
            background-color: #f44336;
            border: none;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            font-size: 12px;
            margin-top: 20px;
        }
        .logout-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">View Course</h1>
        <c:if test="${requestScope.exams eq null}">
            <c:if test="${requestScope.courses.size() > 0}">
                <form action="lecturer" method="POST" class="form-inline">
                    <input type="hidden" name="lid" value="${param.lid}"/>
                    <div class="form-group mr-2">
                        <label for="courseSelect" class="mr-2">Course:</label>
                        <select name="cid" id="courseSelect" class="form-control">
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.id}">
                                    ${c.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">View</button>
                </form>
            </c:if>
        </c:if>
        <c:if test="${requestScope.exams ne null}">
            <div class="alert alert-info">
                <strong>Pass Count:</strong> ${passCount}<br>
                <strong>No Pass Count:</strong> ${noPassCount}
            </div>
            <form action="take" method="GET">
                <input type="hidden" name="cid" value="${param.cid}"/>
                <div class="form-check">
                    <c:forEach items="${requestScope.exams}" var="e">
                        <input type="checkbox" name="eid" value="${e.id}" class="form-check-input" id="exam${e.id}">
                        <label class="form-check-label" for="exam${e.id}">
                            ${e.assessment.name} - (${e.from}: ${e.assessment.weight}%)
                        </label>
                        <br/>
                    </c:forEach>
                </div>
                <button type="submit" class="btn btn-success mt-3">Take</button>
            </form>
        </c:if>
        <a href="${pageContext.request.contextPath}/logout" class="logout-button mt-3">LOGOUT</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlYQ/klO1iioCtJkjtOwueFB6LEnGIfEQMIuZ2sJ6paBIcB/MWJmI6j3nzp" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgPSI9qnDP4WvfxH2N9z84F1tz9L3zvnf9ZZTtmIHvKaF4h3F7T" crossorigin="anonymous"></script>
</body>
</html>
