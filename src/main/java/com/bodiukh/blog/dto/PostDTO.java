package com.bodiukh.blog.dto;

/**
 * @author a.bodiukh
 */
public class PostDTO {

    private String author;

    private String title;

    private String text;

    public PostDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
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

    public void setText(String text) {
        this.text = text;
    }
}
