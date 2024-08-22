package com.synergytech.tms.repository;

import com.synergytech.tms.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
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
    
  
    // find user by email and password
    public User findUserByEmailAndPassword(String email, String password) {
        try {
            return entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
}

