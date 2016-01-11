package com.bodiukh.blog.dao;

import com.bodiukh.blog.domain.User;

/**
 * @author andrii.bodiukh
 */
public interface UserDAO {

    public User findByUsername(final String username);
}
