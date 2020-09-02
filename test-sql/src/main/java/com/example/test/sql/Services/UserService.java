package com.example.test.sql.Services;

import com.example.test.sql.Entities.User;
import com.example.test.sql.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        userRepository.save(user);
        return user;
    }

    public User get(Integer id) {
        return userRepository.findOneById(id);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User update(
            int id,
            String firstName
    ) {
        User user = userRepository.findOneById(id);
        user.setFirstName(firstName);
        userRepository.save(user);
        return user;
    }
}
