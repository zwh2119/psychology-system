package com.lyc.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Result;
import com.lyc.demo.entity.Article;
import com.lyc.demo.entity.User;
import com.lyc.demo.service.IArticleService;
import com.lyc.demo.utils.TokenUtils;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.parser.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("tittle", name);
        User curUser = TokenUtils.getCurrentUser();
        assert curUser != null;
        if(Objects.equals(curUser.getRole(), "ROLE_TEACHER"))
        {
            queryWrapper.eq("author", curUser.getUsername());
        }
        queryWrapper.orderByAsc("id");
        return Result.success(articleService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }

    @DeleteMapping("/{id}")
    public Result deleteArticle(@PathVariable Integer id){
        return Result.success(articleService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteArticleBatch(@RequestBody List<Integer> ids){
        return Result.success(articleService.removeByIds(ids));
    }

    @PostMapping
    public Result addOrModifyArticle(@RequestBody Article article){
//        System.out.println((article.getPublishTime().getClass().getName()));
        article.setAuthor(TokenUtils.getCurrentUser().getUsername());
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getOneArticle(@PathVariable Integer id){
        return Result.success(articleService.getById(id));
    }

}
