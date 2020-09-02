package com.example.test.sql.Repositories;

import com.example.test.sql.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneById(Integer id);

}
