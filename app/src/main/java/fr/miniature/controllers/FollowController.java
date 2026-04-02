package fr.miniature.controllers;

import java.io.IOException;
import fr.miniature.models.User;
import infrastructure.errors.errorWithRedirection.ErrorWithRedirection;
import infrastructure.errors.invalideData.InvalideData;
import infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/follow")
public class FollowController extends GlobalController {
    
    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User userSession) throws ServletException, IOException {
        try{
            //session.setRequest(req).checkSession();
            dataValidator.check(req, "authorID");

            String authorID = dataValidator.escapedForbiddenCharater(req.getParameter("authorID"));                 
            User author = users.findByID(authorID);
            userSession.addAbonnement(author);

            throw new Redirection("/feed");

        }catch(InvalideData error){
            req.setAttribute("error", error);
            resp.sendRedirect("/feed");
            return;
        }catch(ErrorWithRedirection error){
            resp.sendRedirect(error.getPath());
            return;
        }
        
        
        
    }
}
