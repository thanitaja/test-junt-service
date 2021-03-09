package com.example.test.sql.Services;

import com.example.test.sql.Entities.User;
import com.example.test.sql.Repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        // a known set of hardcoded plants.
        List<User> users = createUsers();
        // tell the mock object to return our known set of hardcoded plants, when requested.
        lenient().when(userRepository.findAll()).thenReturn(users);

        // associate the mock object with the obejct we are testing.
//        userRepository.saveAll(users);

    }

    @Test
    @DisplayName("test get User")
    void getUser() {
        given(userRepository.findOneById(1)).willReturn(users.stream().filter(u -> u.getId() == 1).findFirst().get());
        User getUser = userService.get(1);

        assertThat(getUser).isNotNull();
        verify(userRepository).findOneById(1);
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
        userService.delete(1);

        verify(userRepository, times(1)).deleteById(1);
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

    private List<User> createUsers() {
        for (int i = 0 ; i <= 5 ; i++) {
            User user = new User( "firstname " + i+1, "lastname " + i+1, "0928970717", 23 + i);
            user.setId(i+1);
            users.add(user);
        }

        return users;
    }
}
