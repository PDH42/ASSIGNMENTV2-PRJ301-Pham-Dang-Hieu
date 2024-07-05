<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Enter Scores</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-jcSm16ho7P5kn6Bb10d6ClYmvh11w8p8ptCSsPlOxJlE5AlD5Kk5G2sTn0Kx3jV5" crossorigin="anonymous">
    <style>
        body {
            background-color: #ff9800;
            margin: 0;
            padding: 20px;
            font-family: Arial, sans-serif;
        }
        .form-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 1100px;
            margin: auto;
            overflow-x: auto;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .form-control {
            margin-bottom: 10px;
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
            font-size: 14px;
            margin-top: 20px;
        }
        .logout-button:hover {
            background-color: #d32f2f;
        }
        table {
            table-layout: fixed;
            width: 100%;
        }
        th, td {
            word-wrap: break-word;
        }
    </style>
</head>
<body>
    <div class="container form-container">
        <h1 class="text-center">Enter Scores</h1>
        <form action="take" method="POST">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Student Name</th>
                            <c:forEach items="${requestScope.exams}" var="e">
                                <th>
                                    ${e.assessment.name} (${e.assessment.weight}%) <br/>
                                    ${e.from}
                                </th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.students}" var="s">
                            <tr>
                                <td>${s.name}</td>
                                <c:forEach items="${requestScope.exams}" var="e">
                                    <td>
                                        <input type="text" class="form-control text-center" name="score${s.id}_${e.id}"
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
            </div>
            <input type="hidden" name="cid" value="${param.cid}" />
            <button type="submit" class="btn btn-success">Save</button>
        </form>
        <a href="${pageContext.request.contextPath}/logout" class="logout-button">LOGOUT</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlYQ/klO1iioCtJkjtOwueFB6LEnGIfEQMIuZ2sJ6paBIcB/MWJmI6j3nzp" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgPSI9qnDP4WvfxH2N9z84F1tz9L3zvnf9ZZTtmIHvKaF4h3F7T" crossorigin="anonymous"></script>
</body>
</html>
