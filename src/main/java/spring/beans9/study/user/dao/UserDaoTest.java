package spring.beans9.study.user.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.beans9.study.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	
	private UserDao userDao;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
	
	@Before
	public void setUp() {
		// ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		this.userDao = this.context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("a1","test1","test1");
		this.user2 = new User("a2","test2","test2");
		this.user3 = new User("a3","test3","test3");
		this.user4 = new User("a4","test4","test4");
		
	}
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		userDao.deleteAll();
		assertThat(userDao.getCount(), equalTo(0));
		
		userDao.add(user1);
		userDao.add(user2);
		assertThat(userDao.getCount(), equalTo(2));

		User dbuser1 = userDao.get(user1.getId());
		assertThat(user1.getName(), equalTo(dbuser1.getName()));
		assertThat(user1.getPassword(), equalTo(dbuser1.getPassword()));
		
		User dbuser2 = userDao.get(user2.getId());
		assertThat(user2.getName(), equalTo(dbuser2.getName()));
		assertThat(user2.getPassword(), equalTo(dbuser2.getPassword()));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		userDao.deleteAll();
		assertThat(userDao.getCount(), equalTo(0));
		
		userDao.get("unknow");
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), equalTo(0));
		
		userDao.add(user1);
		assertThat(userDao.getCount(), equalTo(1));
		
		userDao.add(user2);
		assertThat(userDao.getCount(), equalTo(2));
		
		userDao.add(user3);
		assertThat(userDao.getCount(), equalTo(3));
		
		userDao.add(user4);
		assertThat(userDao.getCount(), equalTo(4));
	}
	
	@Test
	public void getAll() throws SQLException {
		userDao.deleteAll();
		
		userDao.add(user1);
		List<User> users1 = userDao.getAll();
		assertThat(users1.size(), equalTo(1));
		checkSameUser(user1, users1.get(0));
		
		userDao.add(user2);
		List<User> users2 = userDao.getAll();
		assertThat(users2.size(), equalTo(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		userDao.add(user3);
		List<User> users3 = userDao.getAll();
		assertThat(users3.size(), equalTo(3));
		checkSameUser(user1, users3.get(0));
		checkSameUser(user2, users3.get(1));
		checkSameUser(user3, users3.get(2));
		
		userDao.add(user4);
		List<User> users4 = userDao.getAll();
		assertThat(users4.size(), equalTo(4));
		checkSameUser(user1, users4.get(0));
		checkSameUser(user2, users4.get(1));
		checkSameUser(user3, users4.get(2));
		checkSameUser(user4, users4.get(3));
	}
	
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getName(), equalTo(user2.getName()));
		assertThat(user1.getPassword(), equalTo(user2.getPassword()));
		assertThat(user1.getId(), equalTo(user2.getId()));
	}
	
	public static void main(String[] args) {
		JUnitCore.main("spring.beans9.study.user.dao.UserDaoTest");
	}
}
