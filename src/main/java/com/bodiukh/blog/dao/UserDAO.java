package com.bodiukh.blog.dao;

import com.bodiukh.blog.domain.User;

/**
 * @author a.bodiukh
 */
public interface UserDAO {

    User findByUsername(String username);

}
