package infrastructure.session;

import java.io.IOException;

import fr.miniature.models.User;
import infrastructure.database.UserRepository;
import infrastructure.errors.errorWithRedirection.ErrorWithRedirection;
import infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class Session {
    private static Session instance;
    HttpSession session;
    private HttpServletRequest req;
    private UserRepository users = UserRepository.getInstance();
    private final String KEY = "userID"; 

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public Session setRequest(HttpServletRequest req){
        this.req = req;
        return this;
    }
   
    public void checkSession()throws ServletException, IOException{
        this.session = req.getSession(false);
        sessionExist();
        findUser();
    }

    public void checkSessionAndRedirectIfExist()throws ServletException, IOException{
      
       if(session != null){
            String userID = (String) session.getAttribute(KEY);
            if(userID != null){
                redirectToFeed();
            }
        
       }
        
    }

    private void sessionExist(){
        if (session == null) {
            throw new ErrorWithRedirection("/connexion");           
        }
    }

    private void findUser(){
        String userID = (String) session.getAttribute(KEY);
        User userSession = users.findByID(userID);
        if (userSession == null) {
            throw new ErrorWithRedirection("/connexion");
            
        }
        
    }

    public User getUserSession(){
        String userID = (String) session.getAttribute(KEY);
        User userSession = users.findByID(userID);
        return userSession;
    }

    public void redirectToFeed(){
        throw new Redirection("/feed");
    }

    public void createSession(HttpServletRequest req, String userID){
        if(session == null){
            this.session = req.getSession(false);
        }
        session.setAttribute(KEY, userID);
    }

    public void deleteSession(){
        session.removeAttribute(KEY);
        throw new ErrorWithRedirection("/");
    }

}
