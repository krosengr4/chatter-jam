package data;

import models.Post;

import java.util.List;

public interface PostDao {

	List<Post> getAll();

	List<Post> getFromUser(String userName);

	Post getById(int postId);

	Post create(Post post);

	void delete(int postId);

}
