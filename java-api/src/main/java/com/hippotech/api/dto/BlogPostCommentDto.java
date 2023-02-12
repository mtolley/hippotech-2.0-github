package com.hippotech.api.dto;

public class BlogPostCommentDto {
    private long blogPostId;
    private String text;

    public long getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(long blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
