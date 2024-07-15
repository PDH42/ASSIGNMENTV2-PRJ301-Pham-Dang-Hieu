<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: darkorange;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .container {
            background-color: white;
            padding: 40px;
            margin: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 800px;
            box-sizing: border-box;
        }
        .profile-info {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .profile-info p {
            margin: 5px 0;
            line-height: 1.5;
        }
        .profile-info strong {
            display: inline-block;
            width: 150px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Profile</h2>
        <div class="profile-info">
            <p><strong>Username:</strong> ${user.username}</p>
            <p><strong>Display Name:</strong> ${user.displayname}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Phone:</strong> ${user.phone}</p>
            <p><strong>Address:</strong> ${user.address}</p>
        </div>
    </div>
</body>
</html>
