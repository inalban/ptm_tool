package com.project.management;

import com.project.management.model.User;
import com.project.management.repositories.UserRepository;
import com.project.management.services.CustomUserDetailsService;
import com.project.management.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementApplicationTests {

//
//	@MockBean
//	private UserService userService;
@Autowired
private UserService userService;
	@Test
	public void setAndGetUserCredentials() throws Exception {
		User user=new User();
		String uName="testadminn@gmail.com";
		String pass="012345in";
		String fullName="Test Admin";
		user.setUsername(uName);
		user.setPassword(pass);
		user.setFullName(fullName);
		userService.saveUser(user);
		CustomUserDetailsService cs=new CustomUserDetailsService();
		System.out.println("@@@@@@@@@@@@@@@ "+cs.loadUserByUsername("testadminn@gmail.com"));


		Assert.assertEquals("Set and get User name success",uName,user.getUsername());
		Assert.assertEquals("Set and get password success",pass,user.getPassword());
		Assert.assertEquals("Set and get full name success",fullName,user.getFullName());

		System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getFullName());



	}

}
