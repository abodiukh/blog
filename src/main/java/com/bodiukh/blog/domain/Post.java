package com.bodiukh.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author a.bodiukh
 */
@Entity
@Table(name="post")
public class Post {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="author")
    private String author;

    @Column(name="text")
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
