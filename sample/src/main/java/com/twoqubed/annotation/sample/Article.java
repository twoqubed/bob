package com.twoqubed.annotation.sample;

import com.twoqubed.annotation.Built;

@Built
public class Article {

    private final String id;
    private final int department;
    private final String status;

    public Article(String id, int department, String status) {
        this.id = id;
        this.department = department;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }

}