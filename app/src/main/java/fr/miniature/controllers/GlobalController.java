package fr.miniature.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import fr.miniature.models.User;
import infrastructure.database.CommentRepository;
import infrastructure.database.PostRepository;
import infrastructure.database.UserRepository;
import infrastructure.errors.errorWithRedirection.ErrorWithRedirection;
import infrastructure.errors.invalideData.InvalideData;
import infrastructure.session.Session;
import infrastructure.validator.DataValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalController extends HttpServlet {

    protected Session session = Session.getInstance();
    protected UserRepository users = UserRepository.getInstance();
    protected PostRepository posts = PostRepository.getInstance();
    protected CommentRepository comments = CommentRepository.getInstance();
    protected DataValidator dataValidator = DataValidator.getInstance();
    private List<String> needUserSessionForMethodGet = Arrays.asList("/feed", "/comments");
    private List<String> needUserSessionForMethodPost = Arrays.asList("/feed", "/comments", "/follow");
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            handleGet(req, resp);
            String path = req.getServletPath();

            if(needUserSessionForMethodGet.contains(path)){
                System.out.println(path);
                session.setRequest(req).checkSession();
                User user = session.getUserSession();
                handleGet(req, resp, user);
            }else{
                handleGet(req, resp);
            }
            

        }catch(ErrorWithRedirection error){
            resp.sendRedirect(error.getPath());
            return;
        }
    }

    protected void handleGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        try{
            /*req.set
            Map<String, String[]> map = req.getParameterMap();
            map.forEach(arr -> arr.forEach(v -> S));*/
           if(needUserSessionForMethodPost.contains(path)){               
                session.setRequest(req).checkSession();
                User user = session.getUserSession();
                handlePost(req, resp, user);
            }else{
                handlePost(req, resp);
            }
            

        }catch(ErrorWithRedirection error){
            resp.sendRedirect(error.getPath());
            return;
        }catch(InvalideData error){
            req.setAttribute("error", error);
            req.getRequestDispatcher(path+".jsp").forward(req, resp);
            return;
        }catch(Error error){            
            req.setAttribute("error", error);
            req.getRequestDispatcher(path+".jsp").forward(req, resp);
            return;
        }
    }


    protected void handlePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }
}
