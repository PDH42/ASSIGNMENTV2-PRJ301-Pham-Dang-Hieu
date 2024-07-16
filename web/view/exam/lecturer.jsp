<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecturer Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/lecturer.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <span class="navbar-text">Welcome, ${sessionScope.user.displayname} So Cute And Handsome (Lecturer)</span>
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
    <div class="container mt-5">
        <c:if test="${requestScope.exams eq null}">
            <c:if test="${requestScope.courses.size() > 0}">
                <form action="lecturer" method="POST">
                    <input type="hidden" name="lid" value="${param.lid}"/>
                    <div class="form-group">
                        <label for="course-select">Select a course:</label>
                        <select name="cid" id="course-select" class="form-control">
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.id}">
                                    ${c.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">View</button>
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
                    <button type="submit" class="btn btn-success">Take</button>
                </div>
            </form>
        </c:if>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
