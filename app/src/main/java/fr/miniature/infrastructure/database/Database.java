package fr.miniature.infrastructure.database;
/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import domain.models.database.DatabaseInterface;
import domain.models.entities.EntityEnum;
import domain.models.entities.EntityInterface;
import fr.miniature.models.Comments;
import fr.miniature.models.Posts;
import fr.miniature.models.User;
import fr.miniature.models.Users;

public class Database implements DatabaseInterface {
    private static DatabaseInterface instance;
    //private Users users = new Users();
    //private Posts posts = new Posts();
    //private Comments comments = new Comments();
    private final List<Object> store = new ArrayList<>();

    public static DatabaseInterface getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    

    public void connect(){
        System.out.println("connexion à la base de donnée");
    }

    private List<Object> getAll(){
        return store;
    }

    public <Object> Optional<Object> findByID(Predicate<Object> predicat){
        return getAll().stream()
                .filter(type::isInstance)
                .filter(predicat)
                .map(type::cast)
                .findFirst();
        
        
    }


    public <T extends EntityInterface> List<T> getEntities(ArrayList<T> list, Class<T> type){
        switch (type) {
            case User.class:
                return users.find().stream().map(type::cast).toList();
                
            case EntityEnum.post:
                return posts.getPosts();
        
            case EntityEnum.comment:
                return comments.getComments();
            default:
                throw new Error("Entité inconnue.");
        }
        
    }
}
*/