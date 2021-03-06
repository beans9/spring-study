package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import spring.beans9.study.user.domain.User;

public class UserDao {
	//private ConnectionMaker connectionMaker;
	/*public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}*/
	
	private DataSource dataSource;
	public UserDao() {}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		// Class.forName("com.mysql.jdbc.Driver");
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) value (?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();
		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		// Class.forName("com.mysql.jdbc.Driver");
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select * from users where id=?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		User user = null;
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		

		rs.close();
		ps.close();
		c.close();

		if(user==null) throw new NullPointerException();
		return user;
	}
	
	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		ps.close();
		c.close();
	}
	
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select count(*) from users");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
}
