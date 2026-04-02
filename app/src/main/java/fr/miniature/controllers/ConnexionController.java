package fr.miniature.controllers;

import java.io.IOException;

import fr.miniature.infrastructure.models.User;
import fr.miniature.infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/connexion")
public class ConnexionController extends GlobalController {

    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session.setRequest(req).checkSessionAndRedirectIfExist();
        req.getRequestDispatcher("/connexion.jsp").forward(req, resp);
        return;
    }
    
    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dataValidator.check(req, "pseudo", "password");
        
        String pseudo = dataValidator.escapedForbiddenCharater(req.getParameter("pseudo"));
        String password = dataValidator.escapedForbiddenCharater(req.getParameter("password"));
        
        // créer un user
        User user = users.findByNameAndPassword(pseudo, password);            
        session.createSession(req, user.getID());

        throw new Redirection("/feed");      
    }
}
