package com.daw.swapp.repository.usersJPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.swapp.model.users.User;

public interface UsersJPARepository extends JpaRepository<User, Integer> {
}
