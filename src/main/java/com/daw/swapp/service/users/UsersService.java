package com.daw.swapp.service.users;

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
        return usersRepository.getAllUsers();
    }

    public int addUser(User user) {
        return usersRepository.addUser(user);
    }

    public boolean deleteUser(int id) {
        return usersRepository.deleteUser(id);
    }
}
