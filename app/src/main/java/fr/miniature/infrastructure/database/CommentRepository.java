package fr.miniature.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import fr.miniature.domain.models.repositories.fromInterface;
import fr.miniature.domain.repositories.comments.CommentsDBInterface;
import fr.miniature.infrastructure.models.Comment;
import fr.miniature.infrastructure.models.Comments;


public class CommentRepository implements CommentsDBInterface, fromInterface<Comment> {
    private static CommentRepository instance;
    private Comments comments = Comments.getInstance();

    
    public static CommentRepository getInstance(){
        if(instance == null){
            instance = new CommentRepository();
        }
        return instance;
    }

     public void save(Comment newComment){
        comments.addComment(newComment);
    }

    public Comment findByID(String ID){
        return comments.findByID(ID);
       // return (Comments) database.getEntityWithId(ID, EntityEnum.Comments);        
    }

    
    public ArrayList<Comment> find(){
        return comments.find();
        /*ArrayList<Comments> CommentssList;
        CommentssList = database.getEntities(CommentssList);
        return CommentssList; */
    }

    public List<Comment> findFromID(String ID){
        return comments.getCommmentsFor(ID);
    }

   // public ArrayList<T> find(int limit);
    public void deleteByID(String ID){
        // on surprime le Comments
    }
}
