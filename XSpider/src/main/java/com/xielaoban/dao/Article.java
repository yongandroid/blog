package com.xielaoban.dao;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by SeanWu on 2017/2/13.
 */

public class Article {
    private Long id;
    private String title;
    private String date;
    private String content;
    private String origin;
    private String link;

    /*protected Article(){};*/


    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public Article setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Article setDate(String date) {
        this.date = date;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Article setLink(String link) {
        this.link = link;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return title + "," + link + ',' + date;
    }
}

