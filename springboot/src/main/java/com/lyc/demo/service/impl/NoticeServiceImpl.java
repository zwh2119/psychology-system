package com.lyc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.entity.Notice;
import com.lyc.demo.mapper.NoticeMapper;
import com.lyc.demo.service.IMenuService;
import com.lyc.demo.service.INoticeService;
import com.lyc.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {


}
