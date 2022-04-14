package com.cmpe202.app.hotelbooking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.RoleRepository;
import com.cmpe202.app.hotelbooking.repository.UserRepository;
import com.cmpe202.app.hotelbooking.service.UserService;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceTest {
	
	  @Mock
	   private UserRepository mockUserRepository;
	  
	  @Mock
	    private RoleRepository mockRoleRepository;
	  
	  @Mock
	  private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	  
	  private UserService userServiceUnderTest;
	  private User user;
	  
	  @Before
	  public void setUp() {
		  initMocks(this);
		  userServiceUnderTest = new UserService(mockUserRepository,mockRoleRepository,
                  mockBCryptPasswordEncoder);
		  
		  user = User.builder()
	                .id(1)
	                .firstName("Gopi")
	                .lastName("Vinodh")
	                .email("test@test.com")
	                .active(true)
	                .build();
		  Mockito.when(mockUserRepository.save(any()))
          .thenReturn(user);
		  
		  Mockito.when(mockUserRepository.findByEmail(anyString()))
          .thenReturn(user);
	  }
	  
	  @Test
	    public void testFindUserByEmail() {
	        // Setup
	        final String email = "test@test.com";

	        // Run the test
	        final User result = userServiceUnderTest.findUserByEmail(email);

	        // Verify the results
	        assertEquals(email, result.getEmail());
	    }
	  	
	  
	  @Test
	    public void testSaveUser() {
	        // Setup
	        final String email = "test@test.com";

	        // Run the test
	        User result = userServiceUnderTest.createUser(User.builder().build());

	        // Verify the results
	        assertEquals(email, result.getEmail());
	    }

}
