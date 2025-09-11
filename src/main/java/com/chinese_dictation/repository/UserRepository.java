package com.chinese_dictation.repository;

import com.chinese_dictation.model.dto.response.UserProgressResponse;
import com.chinese_dictation.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String email);
}
