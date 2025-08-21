package data;

import models.Comment;

import java.util.List;

public interface CommentDao {

	List<Comment> getAll();

	List<Comment> getAllFromPost(int postId);

	Comment add(Comment comment);

	void delete(int commentId);

}
