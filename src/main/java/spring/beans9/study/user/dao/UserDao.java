package spring.beans9.study.user.dao;

import java.util.List;

import spring.beans9.study.user.domain.User;

public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	int update(User user);
}
