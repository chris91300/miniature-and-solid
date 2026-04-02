package fr.miniature.domain.models.commentForClient;

import fr.miniature.infrastructure.models.Comment;
import fr.miniature.infrastructure.models.User;


public record CommentClient(Comment comment, User author){};


