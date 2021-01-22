package com.kang.spring_boot.service;





import com.kang.spring_boot.model.User;

import java.util.List;

public interface UserService {

    List<User> GetAllUsers();

    User getUserByName(String username);

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
