package com.lyc.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Result;
import com.lyc.demo.entity.Article;
import com.lyc.demo.service.IArticleService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        queryWrapper.orderByDesc("id");
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

}
