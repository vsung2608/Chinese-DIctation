package com.chinese_dictation.repository;

import com.chinese_dictation.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(com.chinese_dictation.model.enums.Role name);
}
