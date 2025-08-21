package data.mysql;

import config.DatabaseConfiguration;
import data.PostDao;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class MySqlPostDao extends MySqlBaseDao implements PostDao {

	static BasicDataSource dataSource = DatabaseConfiguration.setDataSource();

	public MySqlPostDao(DataSource dataSource) {
		super(dataSource);
	}

}
