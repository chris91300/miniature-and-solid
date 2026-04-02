package fr.miniature.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import fr.miniature.domain.models.repositories.fromInterface;
import fr.miniature.domain.repositories.posts.PostsDBInterface;
import fr.miniature.infrastructure.models.Post;
import fr.miniature.infrastructure.models.Posts;

public class PostRepository implements PostsDBInterface, fromInterface<Post> {
    private static PostRepository instance;
    private Posts posts = Posts.getInstance();

    
    public static PostRepository getInstance(){
        if(instance == null){
            instance = new PostRepository();
        }
        return instance;
    }


    public void save(Post newPost){
       posts.addPost(newPost);
    }

    public Post findByID(String ID){
        return posts.findByID(ID);
       // return (Post) database.getEntityWithId(ID, EntityEnum.Post);        
    }

    
    public ArrayList<Post> find(){
        return posts.find();
        /*ArrayList<Post> PostsList;
        PostsList = database.getEntities(PostsList);
        return PostsList; */
    }

    public List<Post> findFromID(String ID){
        return posts.getPostFrom(ID);
    }

   // public ArrayList<T> find(int limit);
    public void deleteByID(String ID){
        // on surprime le Post
    }
}
