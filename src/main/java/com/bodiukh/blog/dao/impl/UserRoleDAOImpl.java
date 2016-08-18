package com.bodiukh.blog.dao.impl;

import java.util.List;

import com.bodiukh.blog.dao.UserRoleDAO;
import com.bodiukh.blog.domain.UserRole;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public UserRole findByName(final String name) {
        List<UserRole> roles = sessionFactory.getCurrentSession()
                .createQuery("from UserRole where role=:rolename")
                .setParameter("rolename", name)
                .list();
        return roles.get(0);
    }

    @Override
    public UserRole getById(final String id) {
        return (UserRole) sessionFactory.getCurrentSession().get(UserRole.class, new Integer(id));
    }
}
