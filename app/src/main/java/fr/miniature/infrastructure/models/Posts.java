package fr.miniature.infrastructure.models;

import java.util.ArrayList;
import java.util.Collections;

public final class Posts {
    private static Posts instance;
    public ArrayList<Post> posts = new ArrayList<>();

    public static Posts getInstance(){
        if(instance == null){
            instance = new Posts();
        }
        return instance;
    }
    
    public Post findByID(String ID){
        Post currentPost = null;
        for(Post post: posts){
            if(ID.equals(post.getID())){
                currentPost = post;
            }
        }
        if(currentPost == null) throw new Error("post non trouvé.");
        return currentPost;
    }
    // read
    public ArrayList<Post> find() {
        ArrayList<Post> smallPosts = getPosts(10);
        ArrayList<Post> postsInversed = new ArrayList<>(smallPosts);
        Collections.reverse(postsInversed);
        return postsInversed;
    }

    // read
    public ArrayList<Post> getPosts(int max){
        ArrayList<Post> postList = new ArrayList<>();
        int compteur = 0;

        for(Post post: posts){
            if(compteur < max){
                postList.add(post);
                compteur++;
            }else{
                break;
            }           
        }

        return postList;
    }

    // read
    public ArrayList<Post> getPostFrom(String authorID){
        ArrayList<Post> authorPosts = new ArrayList<Post>();;

        for(Post post: posts){
            if(post.getUserID().equals(authorID)){
                authorPosts.add(post);
            }
        }

        ArrayList<Post> postsInversed = new ArrayList<>(authorPosts);
        Collections.reverse(postsInversed);
        return postsInversed;

    }

   
    // read
    public Post getPost(String id){
        Post currentPost = null;
        for(Post post: posts){
            if(post.getID().equals(id)){
                currentPost = post;
                break;
            }
        }

        return currentPost;
    }


    // write
     public ArrayList<Post> addPost(Post post){
        posts.add(post);
        return find();
        
    }

}