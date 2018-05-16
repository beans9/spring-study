package spring.beans9.study.user.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	
	@Test(expected=NullPointerException.class)
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
	
	public static void main(String[] args) {
		JUnitCore.main("spring.beans9.study.user.dao.UserDaoTest");
	}
}
