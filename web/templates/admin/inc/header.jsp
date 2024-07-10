<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            color: #333;
            text-align: center;
            padding: 20px;
        }
        nav {
            background-color: #333;
        }
        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
        }
        nav ul li {
            margin: 0 15px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 15px 20px;
            display: block;
        }
        nav ul li a:hover {
            background-color: #575757;
        }
    </style>
</head>
<body>
    <nav>
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin">Home</a>
            </li>
        </ul>
    </nav>
</body>
</html>
