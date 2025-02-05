package com.chat.demo.repository;

// import com.chat.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.demo.entity.User;
import java.util.Optional;

// 유저 저장소
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    // List<User> searchUsers(@Param("keyword") String keyword);

    Optional<User> findByUsername(String username);

}