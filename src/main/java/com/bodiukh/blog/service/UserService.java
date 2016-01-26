package com.bodiukh.blog.service;

import com.bodiukh.blog.domain.User;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

}
