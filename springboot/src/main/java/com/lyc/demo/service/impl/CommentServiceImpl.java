package com.lyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.entity.Comment;
import com.lyc.demo.mapper.CommentMapper;
import com.lyc.demo.service.ICommentService;
import com.lyc.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    IUserService userService;

    @Override
    public List<Comment> findComments(Integer articleId) {

        List<Comment> commentList = this.list();
        for(Comment comment : commentList)
        {
            if(comment.getReply()!=null)
            {
                Comment parent = this.getById(comment.getReply());
                if(parent==null)
                {
                    this.removeById(comment.getId());
                }
            }
        }
        for(Comment comment : commentList)
        {
            if(comment.getReply()!=null)
            {
                Comment parent = this.getById(comment.getReply());
                if(parent==null)
                {
                    this.removeById(comment.getId());
                }
            }
        }

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aid", articleId);
        queryWrapper.orderByDesc("publish_time");

        List<Comment> comments = list(queryWrapper);

        for(Comment comment : comments)
        {

            comment.setUser(userService.getById(comment.getUid()));
            if(comment.getReply()!=null)
            {
//                System.out.println(comment.getId());
                comment.setParentNickname(userService.getById(this.getById(comment.getReply()).getUid()).getNickname());
            }

        }

        List<Comment> parentComments = comments.stream().filter(comment -> comment.getReply()==null).collect(Collectors.toList());

        for(Comment comment:parentComments)
        {
            comment.setChildren(comments.stream().filter(c -> Objects.equals(comment.getId(), findRootReplyId(c.getId()))).collect(Collectors.toList()));
        }

        return parentComments;
    }

    private Integer findRootReplyId(Integer id)
    {
        Comment comment = this.getById(id);
        if(comment.getReply()==null)
            return null;
//        System.out.println(".............................................");
        while(id!=null)
        {
//            System.out.println("id:"+id);

            id = comment.getReply();
            if(id==null)
                break;
            else
                comment = this.getById(id);

        }
        assert comment != null;
        return comment.getId();
    }
}
