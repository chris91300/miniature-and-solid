package fr.miniature.infrastructure.models;

import fr.miniature.domain.models.publication.Publication;

public class Comment extends Publication{
    private String idPost;
  
    public Comment(String userID, String content, String idPost) {
        super(userID, content);
        this.idPost = idPost;

    }

    public String getPostID(){
        return this.idPost;
    }

}
