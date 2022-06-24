package tn.esprit.spring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.impl.UserServiceImpl;

@SpringBootTest(classes = UserServiceImpl.class)
@ContextConfiguration
class UserServiceImplTest {
	@Autowired
	IUserService us;
	
	@MockBean
	private UserRepository rp;
	
	@Test
	@Order(1)
	public void testRetrieveAllUsers() {
	List<User> listUsers = us.retrieveAllUsers();
	Assertions.assertEquals(0, listUsers.size());
	}
	
	/*@Test
	@Order(2)
	public void testAddUser() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User("Chiheb", "Ben Rejeb", d, Role.INGENIEUR)	;
		User userAdded = us.addUser(u);
		Assertions.assertEquals(u.getLastName(), userAdded.getLastName());
	}
	
	@Test
	@Order(3)
	public void testModifyUser() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User(1L, "Chiheb", "Ben Rejeb", d, Role.INGENIEUR)	;
		User userUpdated = us.updateUser(u);
		Assertions.assertEquals(u.getLastName(), userUpdated.getLastName());
	}
	
	@Test
	@Order(4)
	public void testRetrieveUser() {
		User userRetrieved = us.retrieveUser("1");
		Assertions.assertEquals(1L, userRetrieved.getId());
	}
	
	@Test
	@Order(5)
	public void testDeleteUser() {
		us.deleteUser("3");
		Assertions.assertNull(us.retrieveUser("3"));
		}*/

}
