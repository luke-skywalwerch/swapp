package com.daw.swapp.service.usersJPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.swapp.model.users.User;
import com.daw.swapp.repository.usersJPA.UsersJPARepository;

@Service
public class UsersJPAService {

    @Autowired
    private UsersJPARepository usersJPARepository;

    public List<User> listUsers() {
        return usersJPARepository.findAll();
    }

    public User addUser(User user) {
        return usersJPARepository.save(user);
    }

    public boolean deleteUser(int id) {
        if (usersJPARepository.existsById(id)) {
            usersJPARepository.deleteById(id);
            return true;
        }
        return false;
    }
}
