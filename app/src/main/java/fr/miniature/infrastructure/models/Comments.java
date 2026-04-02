package fr.miniature.infrastructure.models;

import java.util.ArrayList;
import java.util.Collections;

public class Comments {
   

    private static Comments instance;
    ArrayList<Comment> comments = new ArrayList<>();

    public static Comments getInstance(){
        if(instance == null){
            instance = new Comments();
        }
        return instance;
    }

    public void addComment(Comment comment){
    comments.add(comment);
    }

    public Comment findByID(String ID){
        Comment currentComment = null;

        for(Comment comment : comments){
            if(ID.equals(comment.getID())){
                currentComment = comment;
            }
        }

        if(currentComment == null) throw new Error("Commentaire non trouvé.");

        return currentComment;
    }

    public ArrayList<Comment> find() {
        return comments;
    }

    public ArrayList<Comment> getCommmentsFor(String postID){
        ArrayList<Comment> list = new ArrayList<>();
        for(Comment comment: comments){
            if(comment.getPostID().equals(postID)){
                list.add(comment);
            }
        }

        Collections.reverse(list);
        return list;
    }

    public Comment getCommentByID(String ID){
        Comment currentComment = null;
        for(Comment comment: comments){
            if(ID.equals(comment.getID())){
                currentComment = comment;
            }
        }

        if(currentComment == null) throw new Error("aucun commentaire avec cette ID");
        return currentComment;
    }
    
}

