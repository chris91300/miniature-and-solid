package fr.miniature.infrastructure.models;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;

import fr.miniature.domain.models.entities.EntityInterface;
import fr.miniature.domain.models.generator.GeneratorInterface;
import fr.miniature.domain.models.hash.HashInterface;
import fr.miniature.infrastructure.generator.Generator;
import fr.miniature.infrastructure.hash.Hash;

public class User implements EntityInterface {
    private String firstname;
    private String lastname;
    private String password;
    private String pseudo;
    private String id;
    private ArrayList<User> abonnements;
    private HashInterface hash = Hash.getInstance();
    private GeneratorInterface generator = Generator.getInstance();

    public User(String firstname, String lastname, String pseudo, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudo = pseudo;
        this.password = hash.hash( password);
        this.id = generator.generateRandomID();
        this.abonnements = new ArrayList<User>();
    }

    
    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getFullName(){
        return firstname+" "+lastname;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public String getID(){
        return  id;
    }


    public boolean isGoodPassword(String mdp){
        return this.password.equals(hash.hash(mdp));
    }

    
    public void addAbonnement(User author){
        abonnements.add(author);
    }

    public ArrayList<User> getAbonnements(){
        return abonnements;
    }

}
