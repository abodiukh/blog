package com.bodiukh.blog.dao;

import java.util.List;

import com.bodiukh.blog.domain.Post;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author a.bodiukh
 */
@Repository
public class PostDAOImpl implements PostDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Post getPost(final String id) {
        Session session = sessionFactory.getCurrentSession();
        return (Post) session.load(Post.class, new Integer(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addPost(final Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void updatePost(final Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public List<Post> getPostsOfAuthor(final String author) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Post").list();
    }
}
