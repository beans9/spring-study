package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import spring.beans9.study.user.domain.User;

public class AddStatement implements StatementStrategy {
	User user;

	AddStatement() {}

	AddStatement(User user) {
		this.user = user;
	}

	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = null;
		ps = c.prepareStatement("insert into users(id, name, password) value (?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		return ps;
	}
}
