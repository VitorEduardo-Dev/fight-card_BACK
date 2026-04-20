package main.java.fight.model.dao;

import main.java.fight.model.entities.User;

import java.util.List;

public interface UserDao {

    void insert(User user);
    void update(User user);
    void deleteById(Integer id);
    User findById(Integer id);
    List<User> findAll();

}
