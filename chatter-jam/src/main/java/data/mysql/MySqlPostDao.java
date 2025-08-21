package data.mysql;

import config.DatabaseConfiguration;
import data.PostDao;
import models.Post;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MySqlPostDao extends MySqlBaseDao implements PostDao {

	static BasicDataSource dataSource = DatabaseConfiguration.setDataSource();

	public MySqlPostDao(DataSource dataSource) {
		super(dataSource);
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
