package data.mysql;

import data.CommentDao;
import models.Comment;
import models.Post;

import javax.sql.DataSource;
import java.sql.*;
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
		String query = """
				SELECT * FROM comments
				WHERE comment_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, commentId);

			ResultSet results = statement.executeQuery();
			if(results.next()) {
				return mapRow(results);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Comment add(Comment comment) {
		String query = """
				INSERT INTO comments (post_id, content, author, date_posted)
				VALUES (?, ?, ?, ?);
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, comment.getPostId());
			statement.setString(2, comment.getContent());
			statement.setString(3, comment.getAuthor());
			statement.setDate(4, Date.valueOf(comment.getDatePosted()));

			int rows = statement.executeUpdate();
			if(rows > 0) {
				ResultSet key = statement.getGeneratedKeys();
				if(key.next()) {
					int commentId = key.getInt(1);
					return getById(commentId);
				}
			} else {
				System.err.println("ERROR! Failed to post the comment!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
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
