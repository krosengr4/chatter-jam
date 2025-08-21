package data.mysql;

import data.CommentDao;
import models.Comment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentDao extends MySqlBaseDao implements CommentDao {

	public MySqlCommentDao(DataSource dataSource) {
		super(dataSource);
	}

}
