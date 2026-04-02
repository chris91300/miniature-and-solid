package fr.miniature.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import fr.miniature.domain.models.postForClient.PostForClient;
import fr.miniature.infrastructure.errors.invalideData.InvalideData;
import fr.miniature.infrastructure.facade.PostsFacade;
import fr.miniature.infrastructure.models.Post;
import fr.miniature.infrastructure.models.User;
import fr.miniature.infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")
public class FeedController extends GlobalController {
    private PostsFacade postsFacade = new PostsFacade();
    private List<String> actionPossible = Arrays.asList("liker", "creerpost", "follow");

    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException {
        //session.setRequest(req).checkSession();
        List<PostForClient> posts = postsFacade.getPosts();
    
        req.setAttribute("user", userSession);
        req.setAttribute("posts", posts); 
        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
        
        
    }

    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException {
        dataValidator.check(req, "action");

       String action = dataValidator.escapedForbiddenCharater(req.getParameter("action"));
       checkIfActionIsValid(action);

        if (action.equals("creerpost")) {
            savePost(req, resp, userSession);
        }else if(action.equals("liker")){
            likePost(req, resp, userSession);
        }else{
            getFollowingPosts(req, resp, userSession);
        }
    

    }

    private void savePost(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException{
        req.setCharacterEncoding(Charset.forName("UTF-8"));
        dataValidator.check(req, "newPost");
        
        String userID = userSession.getID();
        //String postContent = req.getParameter("newPost");
        String postContent = dataValidator.escapedForbiddenCharater(req.getParameter("newPost"));
       
        Post newPost = new Post(userID, postContent);
        posts.save(newPost);

        throw new Redirection("/feed");
    }

    private void likePost(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException{
        dataValidator.check(req, "postID"); 

        String postID = dataValidator.escapedForbiddenCharater(req.getParameter("postID"));          
        Post currentPost = posts.findByID(postID);
        currentPost.isLike(userSession.getID());

        throw new Redirection("/feed");
    }

    private void getFollowingPosts(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException{
        List<PostForClient> posts;
            String filter = dataValidator.escapedForbiddenCharater(req.getParameter("filter"));
            if(filter == null || filter.equals("all")){
                posts = postsFacade.getPosts();
            }else if(filter.equals("following")){
                List<User> usersFollow = userSession.getAbonnements();
                List<String> followingID = usersFollow.stream().map(u->u.getID()).toList();
                posts = postsFacade.getPosts(followingID);   
            }else{
                posts = postsFacade.getPosts(filter);   
            }
       
        req.setAttribute("user", userSession);
        req.setAttribute("posts", posts); 
        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
        return;

    }

    private void checkIfActionIsValid(String action) {
        boolean isValid = actionPossible.contains(action);
        if(!isValid){
            throw new InvalideData("l'action n'est pas valide");
        }
    }

}
