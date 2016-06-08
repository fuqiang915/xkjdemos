package com.xkj.demos.models;

/**
 * 文章Model
 * Created by fuqiang on 15/12/14.
 */
public class Article {
    private int id;
    private String title;

    public Article(int id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "#" + this.id + ":" + this.title;
    }
}
