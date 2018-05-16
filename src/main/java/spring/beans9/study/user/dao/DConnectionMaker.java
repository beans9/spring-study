package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

	public Connection makeConnection() throws SQLException {
		Connection c = DriverManager.getConnection("c", "spring", "book");
		return c;
	}

}
