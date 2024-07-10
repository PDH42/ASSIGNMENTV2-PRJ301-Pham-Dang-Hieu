<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Sidebar</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f4f4f4;
                padding-left: 250px;
            }
            aside {
                width: 250px;
                height: 100vh;
                background-color: #343a40;
                color: white;
                position: fixed;
                top: 0;
                left: 0;
                overflow-y: auto;
            }
            aside a {
                color: white;
            }
            .nested-ul {
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
        <aside class="bg-dark">
            <a href="#" class="navbar-brand text-white d-block p-3">Brand</a>
            <nav>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/admin/mark" class="nav-link text-white">Input Mark</a>
                    </li>
                    
                </ul>
            </nav>
        </aside>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
