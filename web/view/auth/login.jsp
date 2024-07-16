<!DOCTYPE html>
<!-- Coding By CodingNepal - www.codingnepalweb.com -->
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Form</title>
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <div class="wrapper">
    <h2>LOGIN PAGE</h2>
    <form action="login" method="POST">
      <div class="input-field">
        <input type="text" name="username" required>
        <label>Enter Your Username</label>
      </div>
      <div class="input-field">
        <input type="password" name="password" required>
        <label>Enter Your Password</label>
      </div>
      <button type="submit">Login</button>
    </form>
</body>
</html>