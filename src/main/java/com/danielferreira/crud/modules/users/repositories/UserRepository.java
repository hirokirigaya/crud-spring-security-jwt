package com.danielferreira.crud.modules.users.repositories;

import com.danielferreira.crud.modules.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserDetails findByLogin(String login);
}
