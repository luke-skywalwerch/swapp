package com.daw.swapp.service.users;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.swapp.model.users.User;
import com.daw.swapp.repository.users.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> listUsers() {
        List<User> users = usersRepository.getAllUsers();
        return users;
    }

    public int addUser(User user) {
        try {
            return usersRepository.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user", e);
        }
    }

    public boolean deleteUser(int id) {
        try {
            return usersRepository.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user", e);
        }
    }
}
