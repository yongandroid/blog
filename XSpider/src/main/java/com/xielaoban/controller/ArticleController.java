package com.xielaoban.controller;

import com.xielaoban.dao.Article;
import com.xielaoban.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取文章的相关 Controller
 * Created by SeanWu on 2017/2/13.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleDAO articleDAO;

    @RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
    public Article getArticleById(int id) {
        return articleDAO.findById(id);
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public List<Article> getArticles() {
        return articleDAO.findArticleList();
    }

}
