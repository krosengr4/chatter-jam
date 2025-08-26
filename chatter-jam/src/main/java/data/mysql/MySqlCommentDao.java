package data.mysql;

import data.CommentDao;
import models.Comment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		String query = "SELECT * FROM comments";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				comments.add(mapRow(results));
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return comments;
	}

	@Override
	public List<Comment> getAllFromPost(int postId) {
		List<Comment> comments = new ArrayList<>();
		String query = """
				SELECT * FROM comments
				WHERE post_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, postId);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				comments.add(mapRow(results));
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

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
