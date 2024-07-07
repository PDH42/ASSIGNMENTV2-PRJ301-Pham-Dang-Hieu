<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: darkorange;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .login-form {
                background-color: white;
                padding: 40px;
                border-radius: 10px;
                width: 100%;
                max-width: 400px;
                box-shadow: 0 0 20px rgba(0,0,0,0.2);
            }
            .login-form h2 {
                text-align: center;
                color: green;
                margin-bottom: 20px;
            }
            .message {
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                font-size: 16px;
                text-align: center;
            }
            .message.success {
                background-color: #dff0d8;
                color: #3c763d;
            }
            .message.error {
                background-color: #f2dede;
                color: #a94442;
            }
            .view-lecturer-button {
                display: block;
                width: 100%;
                padding: 15px;
                background-color: blue;
                color: white;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 10px;
            }
            .view-lecturer-button:hover {
                background-color: darkblue;
            }
        </style>
    </head>
    <body>
        <div class="login-form">
            <h2>LOGIN PAGE</h2>
            <form action="login" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-success btn-block">Login</button>
            </form>

            <% 
                String message = (String) request.getAttribute("message");
                String messageType = (String) request.getAttribute("messageType");
                if (message != null) {
            %>
                <div class="message <%= messageType %> mt-3">
                    <%= message %>
                </div>
                <% if ("success".equals(messageType)) { %>
                    <a class="view-lecturer-button" href="exam/lecturer">Go to View Lecturer</a>
                    <a class="view-lecturer-button" href="student/course">Go to View Student</a>
                <% } %>
            <% } %>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
