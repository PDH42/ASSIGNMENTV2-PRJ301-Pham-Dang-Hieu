<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Pass Rate</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/take.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <span class="navbar-text">Welcome, ${sessionScope.user.displayname} So Cute And Handsome</span>
                <div class="ml-auto">
                    <form action="${pageContext.request.contextPath}/lecturer/dashboard" method="GET" class="d-inline">
                        <button type="submit" class="btn btn-outline-primary">Home</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/logout" method="POST" class="d-inline">
                        <button type="submit" class="btn btn-outline-danger">Logout</button>
                    </form>
                </div>
            </div>
        </nav>
    </c:if>
    <div class="container mt-5 pt-5">
        <form action="take" method="POST">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead class="thead-dark">
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
                                        <input type="text" class="form-control" name="score${s.id}_${e.id}"
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
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
