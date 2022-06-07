package com.lyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.demo.entity.Message;

import java.util.List;

public interface IMessageService extends IService<Message> {
    public List<Message> findMessages();
}
