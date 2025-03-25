package com.openclassrooms.starterjwt.security.services;

import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {
  @Autowired
  private UserRepository userRepository;

  private UserDetailsServiceImpl userDetailsService;

  @BeforeEach
  public void setUp() {
    userDetailsService = new UserDetailsServiceImpl(userRepository);
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void loadUserByUsernameTest() {
    // GIVEN
    String username = "yoga@studio.com"; // User with this email must exist in db

    // WHEN
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    // THEN
    assertEquals(username, userDetails.getUsername());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void loadUserByUsernameFailTest() {
    // GIVEN
    String username = "unknown@mail.com"; // This email must not exist in db

    // WHEN & THEN
    assertThrows(UsernameNotFoundException.class, () -> {
      userDetailsService.loadUserByUsername(username);
    });
  }
}
