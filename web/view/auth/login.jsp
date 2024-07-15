<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <style>
            body {
                background-color: darkorange;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .login-form {
                background-color: white;
                padding: 40px;
                border-radius: 10px;
                width: 400px;
                box-shadow: 0 0 20px rgba(0,0,0,0.2);
            }
            .login-form h2 {
                text-align: center;
                color: green;
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"], input[type="password"], input[type="submit"] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 10px 0;
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
            input[type="submit"]:hover {
                background-color: darkgreen;
            }
            .message {
                padding: 10px;
                border-radius: 5px;
                margin-top: 20px;
                text-align: center;
            }
            .message.error {
                background-color: red;
                color: white;
            }
            .message.success {
                background-color: green;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="login-form">
            <h2>LOGIN PAGE</h2>
            <form action="login" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required />
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required />
                </div>
                <input type="submit" value="Login" />
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
