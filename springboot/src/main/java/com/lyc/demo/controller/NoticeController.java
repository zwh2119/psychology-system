package com.lyc.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Result;
import com.lyc.demo.entity.Notice;
import com.lyc.demo.service.INoticeService;
import com.lyc.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    INoticeService noticeService;

    @GetMapping
    public Result getAllNotices(){
        return Result.success(noticeService.list());
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name){
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("tittle", name);
        queryWrapper.orderByDesc("id");
        return Result.success(noticeService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @DeleteMapping("/{id}")
    public Result deleteNotice(@PathVariable Integer id){
        return Result.success(noticeService.removeById(id));

    }

    @PostMapping("/del/batch")
    public Result deleteNoticeBatch(@RequestBody List<Integer> ids){
        return Result.success(noticeService.removeByIds(ids));

    }

    @PostMapping
    public Result addOrModifyNotice(@RequestBody Notice notice){
        notice.setUser(TokenUtils.getCurrentUser().getUsername());
        noticeService.saveOrUpdate(notice);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getOneNotice(@PathVariable Integer id){
        return Result.success(noticeService.getById(id));
    }

}
