package spring.beans9.study.user.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.beans9.study.user.dao.Level;
import spring.beans9.study.user.dao.UserDao;
import spring.beans9.study.user.domain.User;

import static spring.beans9.study.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static spring.beans9.study.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	List<User> users;
	
	@Before
	public void setup() {
		users = Arrays.asList(
			new User("a1","test1","test1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
			new User("a2","test2","test2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 10),
			new User("a3","test3","test3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1),
			new User("a4","test4","test4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
			new User("a5","test5","test5", Level.GOLD, 100, Integer.MAX_VALUE)
		);
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithLevelOutRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), equalTo(userWithLevel.getLevel()));
		assertThat(userWithLevelOutRead.getLevel(), equalTo(Level.BASIC));
	}
	
	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		
		for(User user:users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
	}
	
	@Test
	public void bean() {	
		assertThat(this.userService, notNullValue());
	}
	
	@Test
	public void upgradeAllOrNothing() {
		UserService testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(this.userDao);
		userDao.deleteAll();
		
		for (User user:users) {
			userDao.add(user);
		}
		
		try {
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		} catch (TestUserServiceException e) {
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertThat(userUpdate.getLevel(), equalTo(expectedLevel));
	}
	
	private void checkLevelUpgraded(User user, boolean upgrade) {
		User userUpdate = userDao.get(user.getId());
		if (upgrade) {
			assertThat(userUpdate.getLevel(), equalTo(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), equalTo(user.getLevel()));
		}
	}
	
	static class TestUserService extends UserService{
		private String id;
		private TestUserService(String id) {
			this.id = id;
		}
		@Override
		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) {
				super.upgradeLevel(user);
			}
		}
	}
	
	static class TestUserServiceException extends RuntimeException{
	}
}

