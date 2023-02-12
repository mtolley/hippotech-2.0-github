package com.hippotech.api.model;

import javax.persistence.*;

@Entity
public class BlogPostComment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String email;

    @Column(length = 1000)
    private String text;

    public long getId() { return id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
