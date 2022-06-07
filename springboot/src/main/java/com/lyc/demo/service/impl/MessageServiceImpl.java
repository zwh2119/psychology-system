package com.lyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.entity.Comment;
import com.lyc.demo.entity.Message;
import com.lyc.demo.mapper.MessageMapper;
import com.lyc.demo.service.IMessageService;
import com.lyc.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Resource
    IUserService userService;

    @Override
    public List<Message> findMessages() {

        List<Message> messageList = this.list();
        for(Message message : messageList)
        {
            if(message.getReplyId()!=null){
                Message parent = this.getById(message.getReplyId());
                if(parent==null)
                    this.removeById(message.getId());
            }
        }
        for(Message message : messageList)
        {
            if(message.getReplyId()!=null){
                Message parent = this.getById(message.getReplyId());
                if(parent==null)
                    this.removeById(message.getId());
            }
        }

        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("publish_time");

        List<Message> messages = list(queryWrapper);

        for(Message message : messages)
        {
            message.setUser(userService.getById(message.getUid()));
            if(message.getReplyId()!=null)
            {
//                System.out.println(comment.getId());
                message.setParentNickname(userService.getById(this.getById(message.getReplyId()).getUid()).getNickname());
            }
        }

        List<Message> parentMessages = messages.stream().filter(message -> message.getReplyId()==null).collect(Collectors.toList());

        for(Message message: parentMessages)
        {
            message.setChildren(messages.stream().filter(c -> Objects.equals(message.getId(), findRootReplyId(c.getId()))).collect(Collectors.toList()));
        }

        return parentMessages;
    }

    private Integer findRootReplyId(Integer id){
        Message message = this.getById(id);
        if(message.getReplyId()==null)
            return null;
        while(id!=null){
            id = message.getReplyId();
            if(id==null)
                break;
            else
                message = this.getById(id);
        }
        return message.getId();

    }
}
