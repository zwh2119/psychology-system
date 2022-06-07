package com.lyc.demo.controller;

import com.lyc.demo.common.Result;
import com.lyc.demo.entity.Comment;
import com.lyc.demo.service.ICommentService;
import com.lyc.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    ICommentService commentService;

    @GetMapping("/tree/{id}")
    public Result getCommentTree(@PathVariable Integer id) {
        return Result.success(commentService.findComments(id));
    }

    @PostMapping()
    public Result addComment(@RequestBody Comment comment){
        comment.setUid(TokenUtils.getCurrentUser().getId());
        commentService.saveOrUpdate(comment);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable Integer id){
        return Result.success(commentService.removeById(id));
    }
}
