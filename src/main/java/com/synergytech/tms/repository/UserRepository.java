package com.synergytech.tms.repository;

import com.synergytech.tms.model.User;
import com.synergytech.tms.utils.PasswordUtil;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository() {
        super(User.class);
    }

    public User findUserByEmailAndPassword(String email, String password) {
        try {
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            // Compare the provided password with the stored hashed password
            if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        }
    }
}
