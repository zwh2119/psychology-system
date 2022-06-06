package com.lyc.demo.controller;


import com.lyc.demo.common.Result;
import com.lyc.demo.service.IArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @GetMapping
    public Result getAllArticles()
    {
        return Result.success(articleService.list());
    }

}
