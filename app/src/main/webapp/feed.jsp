<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Error" %>
<%@ page import="java.lang.System" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.miniature.infrastructure.models.User"%>
<%@ page import="fr.miniature.infrastructure.models.Post"%>
<%@ page import="fr.miniature.domain.models.postForClient.PostForClient"%>
<%@ page import="fr.miniature.domain.models.commentForClient.CommentClient"%>
<%@ page import="java.util.Map"%>

<%
Error error = (Error) request.getAttribute("error");

User user = (User) request.getAttribute("user");
List<PostForClient> posts = (List<PostForClient>) request.getAttribute("posts");



%>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/style.css" />
    <title>Document</title>
</head>
<body>
    <main>
        <h1>Votre réseau Miniature</h1>
        <h2>Bonjour <%=user.getPseudo()%></h2>
        <form method="post" action="/login" id="deconnexion" class="as-button">
            <input type="hidden" name="action" value="deconnexion"/>
            <input type="submit" value="se déconnecter"/>
        </form>
        <section>

        <%
            ArrayList<User> abonnements = user.getAbonnements();
            if(abonnements.size() != 0){%>
                <form method="post">
                    <input type="hidden" name="action" value="follow">
                    <div>
                        <input id="all" type="radio" name="filter" value="all" />
                        <label for="all">voir tous les posts</label>
                    </div>
                    <div>
                        <input id="following" type="radio" name="filter" value="following" />
                        <label for="following">voir uniquement mes abonnements</label>
                    </div>
                                   

                <%for(User author: abonnements){
                    %>
                    <div>
                        <input id="<%=author.getID()%>" type="radio" name="filter" value="<%=author.getID()%>" />
                        <label for="<%=author.getID()%>"><%=author.getFullName()%></label>
                    </div>
                   
                    
                <%}%>
                <input type="submit" value="voir" />
                </form>
            <%}
            
        %>
        

        </section>
        <section id="create_post">
            <form id="create_post" method="post">
                
                <textarea name="newPost" placeholder="créer un nouveau post ..."></textarea>
                <input type="hidden" name="action" value="creerpost">
                <input type="submit" value="envoyer" />
                <%
                    if(error != null){%>
                        <p class="error"><%=error.getMessage()%></p>
                    <%}
                %>
            </form>
        </section>
        <section>
        <%
            if(posts.size() == 0){
            %>
            <p>aucun post pour le moment</p>
            <%
            }else{
                for(PostForClient post: posts){
                 %>
                 <article>
                    <%
                        
                        User author = post.author();
                        String authorID = author.getID();
                        Post currentPost = post.post();
                        String postID = currentPost.getID();
                        String href = "/comments?postID="+postID;
                        List<CommentClient> comments = post.comments();
                    %>
                    <p><%=author.getPseudo()%></p>
                    <p class="content"><%=currentPost.getContent()%></p>
                    <form method="post" class="as-button button-style-none">
                        <input type="hidden" name="action" value="liker">
                        <input type="hidden" name="postID" value="<%=currentPost.getID()%>">
                        <button type="submit" class="button-style-none">❤️<sup><%=currentPost.getLike()%></sup></button>

                    </form>
                    <a href=<%=href%> title="ajouter un commentaire">
                        commenter
                    </a>

                    <%
                    
                        if(comments.size() == 0){%>
                            <p>aucun commentaire</p>
                        <%}else{%>
                            <details>
                                <summary>voir les commentaires</summary>
                                <%
                                
                                for(CommentClient comment: comments){%>
                                    <comment>
                                        <p><%=comment.comment().getContent()%></p>
                                    </comment>
                                <%}
                                %>
                            </details>
                        <%}
                    %>
                    
                 </article>
                <%
                }
            }
        %>
        
            
        </section>
    </main>
</body>
</html>