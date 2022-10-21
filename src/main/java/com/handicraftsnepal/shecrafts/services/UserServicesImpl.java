package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserServicesImpl implements UserServices {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void signup(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean authenticate(String userName, String password) throws Exception {


        try {

            User user = entityManager.createQuery("select e from User e where userName =:userName  and password=:password", User.class)
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult();

            return true;

        }catch (NoResultException | NonUniqueResultException e) {
            return false;
        }

    }
}
