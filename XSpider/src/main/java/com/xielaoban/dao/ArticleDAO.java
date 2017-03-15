package com.xielaoban.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SeanWu on 2017/2/13.
 */
@Repository
public class ArticleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public Article findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM x_articles WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Article.class));
    }


    public List<Article> findArticleList() {
        // TODO 这个地方可能需要分页，回头再改吧。。。
        List<Article> articles = jdbcTemplate.query("SELECT * FROM x_articles", (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setLink(rs.getString("link"));
            article.setOrigin(rs.getString("origin"));
            article.setDate(rs.getString("gmt_create"));
            return article;
        });
        return articles;

    }

    public int insertArticleList(final List<Article> articles){
        return 0;
    }


}
