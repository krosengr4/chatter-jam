package data.mysql;

import config.DatabaseConfiguration;
import data.PostDao;
import models.Post;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


	private Post mapRow(ResultSet result) throws SQLException {
		int postId = result.getInt("post_id");
		String title = result.getString("title");
		String content = result.getString("content");
		String author = result.getString("author");
		LocalDate datePosted = result.getTimestamp("date_posted").toLocalDateTime().toLocalDate();

		return new Post(postId, title, content, author, datePosted);
	}

}
