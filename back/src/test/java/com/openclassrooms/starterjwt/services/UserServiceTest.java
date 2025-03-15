package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDelete() {
    Long userId = 1L;
    userService.delete(userId);
    verify(userRepository, times(1)).deleteById(userId);
  }

  @Test
  void testFindById() {
    Long userId = 1L;
    User mockUser = new User();
    mockUser.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    User result = userService.findById(userId);

    assertNotNull(result);
    assertEquals(userId, result.getId());
  }

  @Test
  void testFindById_UserNotFound() {
    Long userId = 1L;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    User result = userService.findById(userId);

    assertNull(result);
  }
}

