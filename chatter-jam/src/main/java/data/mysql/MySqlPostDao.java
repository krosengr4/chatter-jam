package data.mysql;

import config.DatabaseConfiguration;
import data.PostDao;
import models.Post;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostDao extends MySqlBaseDao implements PostDao {

	static BasicDataSource dataSource = DatabaseConfiguration.setDataSource();

	public MySqlPostDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Post> getAll() {
		List<Post> posts = new ArrayList<>();
		String query = "SELECT * FROM posts;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				posts.add(mapRow(results));
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return posts;
	}

	@Override
	public List<Post> getFromUser(String userName) {
		List<Post> posts = new ArrayList<>();
		String query = """
				SELECT * FROM posts
				WHERE author LIKE ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + userName + "%");

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				posts.add(mapRow(results));
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return posts;
	}

	@Override
	public Post getById(int postId) {
		String query = """
				SELECT * FROM posts
				WHERE post_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, postId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			} else {
				System.err.println("ERROR! No posts found with that ID!!!");
			}


		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public Post create(Post post) {
		String query = """
				INSERT INTO posts (title, content, author, date_posted)
				VALUES (?, ?, ?, ?);
				""";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, post.getTitle());
			statement.setString(2, post.getContent());
			statement.setString(3, post.getAuthor());
			statement.setDate(4, Date.valueOf(post.getDatePosted()));

			int rows = statement.executeUpdate();
			if(rows > 0) {
				System.out.println("Success! Your post was created!!!");
				ResultSet key = statement.getGeneratedKeys();

				if(key.next()) {
					int postId = key.getInt(1);
					return getById(postId);
				}
			} else {
				System.err.println("ERROR! Could not create the post!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}


	private Post mapRow(ResultSet result) throws SQLException {
		int postId = result.getInt("post_id");
		String title = result.getString("title");
		String content = result.getString("content");
		String author = result.getString("author");
		LocalDate datePosted = result.getTimestamp("date_posted").toLocalDateTime().toLocalDate();

		return new Post(postId, title, content, author, datePosted);
	}

}
