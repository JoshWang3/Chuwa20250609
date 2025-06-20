package com.chuwa.assignment.pojos;

import java.util.List;

public class BlogPost {
    private String name;
    private List<String> tags;

    public BlogPost(String name, List<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
