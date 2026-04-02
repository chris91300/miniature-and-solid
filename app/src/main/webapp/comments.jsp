<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Error" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.miniature.infrastructure.models.User"%>
<%@ page import="fr.miniature.infrastructure.models.Post"%>
<%@ page import="java.util.Map"%>

<%
Error error = (Error) request.getAttribute("error");
User user = (User) request.getAttribute("user");
Post post = (Post) request.getAttribute("post");
User author = (User) request.getAttribute("author");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/style.css" />
    <title>Poster commentaire</title>
</head>
<body>
    <main>
        <h1>Poster un commentaire</h1>
        <section id="comment_post">
            <h2>Post</h2>
            <p><%=post.getContent()%></p>
            <p>poster par <%=author.getFirstname()%></p>
            <form method="post" action="/follow">
                <input type="hidden" name="authorID" value="<%=author.getID()%>"/>
                <input type="submit" value="s'abonner" />
            </form>
        </section>
        
        <section>
            <form method="post">
                <textarea name="commentContent" placeholder="votre message ...">

                </textarea>
                <input type="hidden" name="postID" value="<%=post.getID()%>"/>
                <input type="submit" value="envoyer" />
            </form>
        </section>
        
    </main>
</body>
</html>