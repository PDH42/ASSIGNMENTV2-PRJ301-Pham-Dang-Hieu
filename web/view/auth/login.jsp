<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body {
                background-color: darkorange;
                font-family: Arial, sans-serif;
            }
            .login-form {
                background-color: white;
                padding: 40px;
                border-radius: 10px;
                width: 400px;
                margin: 50px auto;
                box-shadow: 0 0 20px rgba(0,0,0,0.2);
            }
            .login-form h2 {
                text-align: center;
                color: green;
                margin-bottom: 20px;
            }
            input[type="text"], input[type="password"], input[type="submit"] {
                width: 100%;
                padding: 15px;
                margin: 10px 0;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            input[type="submit"] {
                background-color: green;
                color: white;
                border: none;
                cursor: pointer;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <div class="login-form">
            <h2>LOGIN PAGE</h2>

            <form action="login" method="POST">
                Username: <input type="text" name="username"/> <br/><br/>
                Password: <input type="password" name="password"/> <br/><br/>
                <input type="submit" value="Login"/>
            </form>
            <% 
                String message = (String) request.getAttribute("message");
                String messageType = (String) request.getAttribute("messageType");
                if (message != null) {
            %>
            <div class="message <%= messageType %>">
                <%= message %>
            </div>
            <% } %>
        </div>
    </body>
</html>
