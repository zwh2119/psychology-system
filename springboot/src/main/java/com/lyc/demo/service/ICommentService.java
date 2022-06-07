package com.lyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.demo.entity.Comment;

import java.util.List;

public interface ICommentService extends IService<Comment> {

    public List<Comment> findComments(Integer articleId);
}
