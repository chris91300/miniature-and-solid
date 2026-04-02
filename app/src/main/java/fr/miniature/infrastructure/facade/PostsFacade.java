package fr.miniature.infrastructure.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.miniature.domain.models.commentForClient.CommentClient;
import fr.miniature.domain.models.postForClient.PostForClient;
import fr.miniature.infrastructure.database.CommentRepository;
import fr.miniature.infrastructure.database.PostRepository;
import fr.miniature.infrastructure.database.UserRepository;
import fr.miniature.infrastructure.models.Comment;
import fr.miniature.infrastructure.models.Post;
import fr.miniature.infrastructure.models.User;

public class PostsFacade {
    private UserRepository users = UserRepository.getInstance();
    private PostRepository posts = PostRepository.getInstance();
    private CommentRepository comments = CommentRepository.getInstance();


    public List<PostForClient> getPosts(){  
        List<Post> postsList = posts.find();        
        return getPostsForClient(postsList);
    }


    // filter est soit all soit un id de user
    public List<PostForClient> getPosts(String filter){
        List<Post> postsList = posts.findFromID(filter);
        return getPostsForClient(postsList);
    }

    public List<PostForClient> getPosts(List<String> followingID){

        return followingID.stream()
            .flatMap(idUser -> getPosts(idUser).stream())
            .sorted((o1, o2) -> Long.compare(o2.post().getCreatedAt(), o1.post().getCreatedAt()))
            .toList();

        // List<Post> postsList = new ArrayList<>();
        // for(String ID: followingID){

        // }
        // List<Post> postsList = posts.findFromID(filter);
        // return getPostsForClient(postsList);
    }

    private List<PostForClient> getPostsForClient(List<Post> postsList){
        List<PostForClient> postsClient = new ArrayList<>();

        for(Post post: postsList){
            User authorPost = users.findByID(post.getUserID());
            List<CommentClient> commentsClient = new ArrayList<>();

            for(Comment comment: comments.findFromID(post.getID())){
                User authorComment = users.findByID(comment.getUserID());
                CommentClient commentClient = new CommentClient(comment, authorComment);
                commentsClient.add(commentClient);
            }

            PostForClient postClient = new PostForClient(post, authorPost, commentsClient);
            postsClient.add(postClient);
        }

        return postsClient;
    }
}
