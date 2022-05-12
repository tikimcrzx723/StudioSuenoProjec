package com.studiosueno.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.studiosueno.common.entity.Role;
import com.studiosueno.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User UserCrzx = new User("crzx@gmail.com", "crzx$$$$", "crzx", "crazy");
		UserCrzx.addRole(roleAdmin);

		User savedUser = repo.save(UserCrzx);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userCossette = new User("coss@gma.com", "Coss$$$$", "Cossette", "Potrait");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userCossette.addRole(roleEditor);
		userCossette.addRole(roleAssistant);

		User savedUser = repo.save(userCossette);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUser() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userName = repo.findById(1).get();
		System.out.println(userName);
		assertThat(userName).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userName = repo.findById(1).get();
		userName.setEnabled(true);
		userName.setEmail("asnjfjn@gmail.com");
		
		repo.save(userName);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userCrzx = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userCrzx.getRoles().remove(roleEditor);
		userCrzx.addRole(roleSalesperson);
		
		repo.save(userCrzx);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
}
