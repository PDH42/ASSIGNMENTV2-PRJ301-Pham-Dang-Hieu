<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Schedule" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Schedule</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
                <img src="../img/logo.png" class="top-left-image" alt="Logo"/>
                <div class="ml-auto">
                    <form action="${pageContext.request.contextPath}/student/dashboard" method="GET" class="d-inline">
                        <button type="submit" class="btn btn-outline-primary">Home</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/logout" method="POST" class="d-inline">
                        <button type="submit" class="btn btn-outline-danger">Logout</button>
                    </form>
                </div>
            </div>
        <div class="container1">
            <h1 class="text-center">Student Schedule</h1>
            <div class="table-container">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Course Name</th>
                            <th>Time</th>
                            <th>Slot</th>
                            <th>Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Schedule> scheduleList = (ArrayList<Schedule>) request.getAttribute("scheduleList");
                            for (Schedule schedule : scheduleList) {
                        %>
                        <tr>
                            <td><%= schedule.getCourseName() %></td>
                            <td><%= schedule.getTime() %></td>
                            <td><%= schedule.getSlot() %></td>
                            <td><%= schedule.getDayName() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
