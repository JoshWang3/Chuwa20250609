package com.chuwa.redbook.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author b1go
 * @date 8/22/22 6:52 PM
 */
public class PostDto {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be at most 100 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9 ,.'\"!?()-]+$",
            message = "Title contains invalid characters"
    )
    private String title;

    @Size(max = 250, message = "Description must be at most 250 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9 ,.'\"!?()-]*$",
            message = "Description contains invalid characters"
    )
    private String description;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Set<CommentDto> comments;

    public PostDto() {
    }

    public PostDto(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
