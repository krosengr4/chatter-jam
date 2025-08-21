package data.mysql;

import data.CommentDao;
import models.Comment;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentDao extends MySqlBaseDao implements CommentDao {

	public MySqlCommentDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Comment> getAll() {
		List<Comment> comments = new ArrayList<>();


		return comments;
	}

	@Override
	public List<Comment> getAllFromPost(int postId) {
		List<Comment> comments = new ArrayList<>();


		return comments;
	}

	@Override
	public Comment getById(int commentId) {
		return null;
	}

	@Override
	public Comment add(Comment comment) {
		return null;
	}

	@Override
	public void delete(int commentId) {

	}

	private Comment mapRow(ResultSet result) throws SQLException {
		int commentId = result.getInt("comment_id");
		int postId = result.getInt("post_id");
		String content = result.getString("content");
		String author = result.getString("author");
		LocalDate datePosted = result.getTimestamp("date_posted").toLocalDateTime().toLocalDate();

		return new Comment(commentId, postId, content, author, datePosted);
	}
}
