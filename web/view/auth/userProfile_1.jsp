<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <link href="../sonnt.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <div class="header">
            <div class="welcome">Welcome, ${sessionScope.user.displayname}</div>
            <div class="header-buttons">
                <form action="${pageContext.request.contextPath}/logout" method="POST" class="logout">
                    <input type="submit" value="Logout"/>
                </form>
            </div>
        </div>
    </c:if>
    <div class="container">
        <h2>Profile Student</h2>
        <img src="../img/hieu.jpg" alt="Profile Picture" class="profile-picture"/>
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