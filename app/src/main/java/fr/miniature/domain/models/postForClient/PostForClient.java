package fr.miniature.domain.models.postForClient;

import java.util.List;

import fr.miniature.domain.models.commentForClient.CommentClient;
import fr.miniature.infrastructure.models.Post;
import fr.miniature.infrastructure.models.User;


public record PostForClient(Post post, User author, List<CommentClient> comments ){};

