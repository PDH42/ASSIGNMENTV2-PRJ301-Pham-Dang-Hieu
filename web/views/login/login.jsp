<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <h2>Management Login</h2>
    <form action="<%=request.getContextPath()%>/auth/login" method="post">
        <div>
            <label for="user">User name:</label>
            <input type="text" id="user" name="user">
        </div>
        <div>
            <label for="pass">Password:</label>
            <input type="password" id="pass" name="pass">
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>
