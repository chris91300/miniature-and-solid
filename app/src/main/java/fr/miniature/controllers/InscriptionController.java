package fr.miniature.controllers;

import java.io.IOException;

import fr.miniature.infrastructure.models.User;
import fr.miniature.infrastructure.redirection.Redirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inscription")
public class InscriptionController extends GlobalController {


    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/inscription.jsp").forward(req, resp);
       return;
    }
    
    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dataValidator.check(req, "firstname", "lastname", "pseudo", "password"); 
        
        String firstname = dataValidator.escapedForbiddenCharater(req.getParameter("firstname"));
        String lastname = dataValidator.escapedForbiddenCharater(req.getParameter("lastname"));
        String pseudo = dataValidator.escapedForbiddenCharater(req.getParameter("pseudo"));
        String password = dataValidator.escapedForbiddenCharater(req.getParameter("password"));
        
        // si les données sont valide            
        User user = new User(firstname, lastname, pseudo, password);
        users.save(user);
        System.out.println("user saved");
        session.createSession(req, user.getID());

        throw new Redirection("/feed");
    }
  

   
}
