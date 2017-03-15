package com.xielaoban.controller;

import com.xielaoban.ZGQBYP;
import com.xielaoban.dao.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 爬虫相关的Controller
 * Created by SeanWu on 2017/3/15.
 */
@RestController
@RequestMapping("/spider")
public class Spidercontroller {


    @RequestMapping("/updateArticle")
    public String updateArticle(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException, InterruptedException {

        List<Article> articles = ZGQBYP.getArticles();
        final StringBuilder sb = new StringBuilder();
        articles.forEach(a -> sb.append(a.getTitle()).append("<br />"));
        return sb.toString();
    }

}
