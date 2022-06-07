package com.lyc.demo.controller;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Result;
import com.lyc.demo.entity.Message;
import com.lyc.demo.entity.User;
import com.lyc.demo.service.IMessageService;
import com.lyc.demo.utils.TokenUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Resource
    IMessageService messageService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String nickname){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nickname", nickname);
        queryWrapper.orderByAsc("id");
        return Result.success(messageService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }

    @DeleteMapping("/{id}")
    public Result deleteMessage(@PathVariable Integer id){
        return Result.success(messageService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteMessageBatch(@RequestBody List<Integer> ids){
        return Result.success(messageService.removeByIds(ids));
    }

    @GetMapping("/tree")
    public Result getMessageTree() {
        return Result.success(messageService.findMessages());
    }

    @PostMapping
    public Result addMessage(@RequestBody Message message){
        User user = TokenUtils.getCurrentUser();
        message.setUid(user.getId());
        message.setNickname(user.getNickname());

        messageService.saveOrUpdate(message);
        return Result.success();


    }


}
