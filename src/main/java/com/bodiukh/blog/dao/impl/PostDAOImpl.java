package com.bodiukh.blog.dao.impl;

import java.util.List;

import com.bodiukh.blog.dao.PostDAO;
import com.bodiukh.blog.domain.Post;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author a.bodiukh
 */
@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Post getPost(final String id) {
        Session session = sessionFactory.getCurrentSession();
        return (Post) session.get(Post.class, new Integer(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getPosts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Post").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer addPost(final Post post) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(post);
    }

    @Override
    public void updatePost(final Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getPostsOfAuthor(final String author) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Post as p where p.author.username=:author").setParameter("author", author).list();
    }

    @Override
    public void removePost(final String id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, new Integer(id));
        if (post != null) {
            session.delete(post);
        }
    }

    @Override
    public void publish(String id) {

    }
}
