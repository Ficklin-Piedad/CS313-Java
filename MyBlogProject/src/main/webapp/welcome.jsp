<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link rel="stylesheet" type="text/css" href="blog.css">
    </head>
    <body>
        <div class="logo">
            <img src="bloglogo.jpg" alt="bloglogo" height="150" width="150">
        </div>
        <div class="wrapper">
            <h1>Welcome ${username}!</h1>
            <br /><br />
            <h4>Go ahead and start writing your blog post</h4>
            <form action="CommentPoster" method="get">
                <textarea name="post" rows="20" cols="100"></textarea>

                <br /><br /><br />
                <button type="submit" class="button">Submit Post</button>
                <button type="button" class="button" onclick="window.location = 'PostLoader';">View Other Posts</button><br />
            </form><br/>
            <a href="logout">Click here to log out</a>
        </div>

    </body>
</html>
