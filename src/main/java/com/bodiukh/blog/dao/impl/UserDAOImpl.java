package com.bodiukh.blog.dao.impl;

import java.util.List;

import com.bodiukh.blog.dao.UserDAO;
import com.bodiukh.blog.domain.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author a.bodiukh
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public User findByUsername(final String username) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=:username")
                .setParameter("username", username)
                .list();
        return users.get(0);
    }

    @Override
    public User getById(final String id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, new Integer(id));
    }
}
