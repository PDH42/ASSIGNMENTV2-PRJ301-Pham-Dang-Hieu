<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            padding-top: 20px;
            background-color: #f4f4f4;
        }
        aside {
            width: 250px;
            padding: 15px;
            background-color: #f8f9fa;
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            overflow-y: auto;
        }
        aside nav ul {
            list-style-type: none;
            padding-left: 0;
        }
        aside nav ul li {
            margin: 10px 0;
        }
        aside nav ul li a {
            text-decoration: none;
            color: #343a40;
            font-weight: bold;
            font-size: 16px;
        }
        aside nav ul li a:hover {
            color: #007bff;
        }
        .content {
            margin-left: 270px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <aside>
        <nav>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/user/mark">Mark</a>
                </li>
            </ul>
        </nav>
    </aside>
    <div class="content">
        <!-- Your main content here -->
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
