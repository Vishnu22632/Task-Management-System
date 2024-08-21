package com.synergytech.tms.repository;

import com.synergytech.tms.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserRepository {

    @PersistenceContext(unitName = "tmsDS")
    private EntityManager entityManager;

    @Transactional
    public void createUser(User user) {
        entityManager.persist(user);
    }

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}