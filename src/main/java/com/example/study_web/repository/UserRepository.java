package com.example.study_web.repository;

import com.example.study_web.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);

    // select * from user where 1=1 and email = ???
    Optional<User> findByEmail(String email);

    // select * from user where 1=1 and account = ??? and email = ???
    Optional<User> findByAccountAndEmail(String account, String email);

}
