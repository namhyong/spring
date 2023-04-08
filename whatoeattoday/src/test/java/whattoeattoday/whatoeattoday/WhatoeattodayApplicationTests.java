package whattoeattoday.whatoeattoday;

import org.apache.catalina.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whattoeattoday.whatoeattoday.domain.UserEntity;
import whattoeattoday.whatoeattoday.dto.UserDto;
import whattoeattoday.whatoeattoday.exception.UsernameAlreadyExistsException;
import whattoeattoday.whatoeattoday.repository.UserRepository;
import whattoeattoday.whatoeattoday.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class WhatoeattodayApplicationTests {
@Autowired
	UserService userService;
@Autowired
	UserRepository userRepository;
@PersistenceContext
	EntityManager em;
@MockBean
MockMvc mockMvc;
@Autowired
	WebApplicationContext context;

	@Test
	@DisplayName("sign test")
	void singup() throws  Exception {
		String email = "nam@gmail.com";
		String password = "1234";
		String name = "nam";
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userEntity.setName(name);
		userRepository.save(userEntity);

	}
	@Test
	public void signin() throws Exception{
		String email = "nam@gmail.com";
		String password = "1234";
		String name = "nam";
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.user("shane"))
		);

}

	@Test
	@DisplayName("multi id test")
	public void validateDuplicateTest() throws Exception{

	}
}
