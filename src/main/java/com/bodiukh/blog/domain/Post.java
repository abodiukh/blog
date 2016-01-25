package com.bodiukh.blog.domain;

import javax.persistence.*;
import java.util.Date;


/**
 * @author a.bodiukh
 */
@Entity
@Table(name="post")
public class Post {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Column(name = "author")
    private User author;

    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @Column(name="published")
    private boolean published;

    @Column(name = "date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
