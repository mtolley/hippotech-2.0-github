package com.hippotech.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class BlogPost {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String title;
    private String description;
    @Column(length = 1000)
    private String fullText;
    private String image;
    private String date; // ISO String

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BlogPostComment> comments;

    public List<BlogPostComment> getComments() {
        return comments;
    }

    public void setComments(List<BlogPostComment> blogPostComments) {
        this.comments = blogPostComments;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String title) {
        this.description = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
