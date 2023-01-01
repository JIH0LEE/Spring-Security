package com.example.server.repository;

import com.example.server.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByNickName(String userName);
    Optional<User> findUserByNickName(String userName);
}
