<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="blog.css">
    </head>
    <body>
        <div class="logo">
            <img src="bloglogo.jpg" alt="bloglogo" height="150" width="150">
        </div>
        <div class="wrapper">
            <h1>Welcome to Blog World</h1><br/><br/>

            <h4>Please Log In</h4><br/>
            <form method="post" action="login">
                <label for="txtName">Username:</label>
                <input type="text" id="txtUsername" name="txtUsername"></input>
                <br />
                <label for="txtName">Password:</label>
                <input type="password" id="txtPassword" name="txtPassword"></input>
                <br />
                <input type="submit" value="Login" />
            </form>
        </div>

    </body>
</html>
