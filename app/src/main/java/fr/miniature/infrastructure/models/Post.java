package fr.miniature.infrastructure.models;


import java.util.ArrayList;

import fr.miniature.domain.models.like.LikeInterface;
import fr.miniature.domain.models.publication.Publication;

public class Post extends Publication implements LikeInterface {
  
     ArrayList<String> likeList = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
   
    public Post(String userID, String content) {
        super(userID, content);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public ArrayList<Comment> getComments(){
        return comments;
    }

    public void isLike(String userID){
        if(likeList.contains(userID)){
            likeList.remove(userID);
        }else{
            likeList.add(userID);
        }
        
    }


    public int getLike(){
        return likeList.size();
    }

  
}
