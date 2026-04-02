package fr.miniature.infrastructure.models;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public final class Users {
    private static Users usersInstance;
    private Map<String, User> users = new HashMap<>();


    public void addNewUser(User user){
        String id = user.getID();
        users.put(id, user);
       
    }

    public ArrayList<User> find(){
        ArrayList<User> userList = new ArrayList<>();
        users.forEach( (String, user) -> {
            userList.add(user);
        });

        return userList;
    }
    public User getUserByID(String id){
        User user = users.get(id);
       // if(user == null){
         //   throw new Error("utilisateur inconnu");
        //}

        return user;
    }

    public User getUserForConnexion(String pseudo, String password){
        Collection<User> usersList = users.values();
        
        User userConnected = null;

        for(User user: usersList){
            if(user.getPseudo().equals(pseudo) && user.isGoodPassword(password)){               
                userConnected = user;
                break;
            }
        }

        return userConnected;

    }

    public static Users getInstance(){
        if(usersInstance == null){
            usersInstance = new Users();
        }

        return usersInstance;
    }

    public void delete(String ID){
        users.remove(ID);
    }
}
