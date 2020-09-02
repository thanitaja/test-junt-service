package com.example.test.sql.Services;

import com.example.test.sql.Entities.User;
import com.example.test.sql.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        users.add(new User("1", "last", "0928970717", 23));
        users.add(new User("2", "last", "0928970717", 23));
        users.add(new User("3", "last", "0928970717", 23));
        users.add(new User("5", "last", "0928970717", 23));
    }

    @Test
    @DisplayName("test get User")
    void getUser() {
        User user = new User("1", "last", "0928970717", 23);
        given(userRepository.findOneById(user.getId())).willReturn(users.get(user.getId()));
        User getUser = userService.get(user.getId());

        assertThat(getUser).isNotNull();
        verify(userRepository).findOneById(user.getId());
    }

    @Test
    @DisplayName("test create User")
    void create() {
        User user = new User("lucky", "eiei", "0928970717", 20);
        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));
        User savedUser = userService.create(user);

        assertThat(savedUser.getFirstName()).isNotNull();
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("test delete User")
    void delete() {
        User user = new User("lucky", "eiei", "0928970717", 20);
        userService.delete(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    @DisplayName("test update User")
    void update() {
        String firstName = "Lucky";
        given(userRepository.findOneById(0)).willReturn(users.get(0));
        given(userRepository.save(users.get(0))).willReturn(users.get(0));
        User getUser = userService.update(0, firstName);

        assertThat(getUser).isNotNull();
        verify(userRepository).findOneById(0);
    }
}
