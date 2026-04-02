package fr.miniature.controllers;

import java.io.IOException;

import fr.miniature.models.Comment;
import fr.miniature.models.Post;
import fr.miniature.models.User;
import infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comments")
public class CommentsController extends GlobalController {
    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException {
        dataValidator.check(req, "postID");

        String postID = dataValidator.escapedForbiddenCharater(req.getParameter("postID"));
        Post post = posts.findByID(postID);
        User author = users.findByID(post.getUserID());

        if(post == null || author == null){
            throw new Redirection("/index.html");
        }

        req.setAttribute("user", userSession);
        req.setAttribute("post",post );
        req.setAttribute("author",author );        
        req.getRequestDispatcher("/comments.jsp").forward(req, resp);
              
    }

    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException {
       dataValidator.check(req, "postID", "commentContent");
       
        String postID = dataValidator.escapedForbiddenCharater(req.getParameter("postID")); 
        String commentContent = dataValidator.escapedForbiddenCharater(req.getParameter("commentContent"));        
        Comment newComment = new Comment(userSession.getID(), commentContent, postID);
        comments.save(newComment);

        throw new Redirection("/feed");
    }
}
