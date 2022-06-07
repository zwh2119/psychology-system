package com.lyc.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Result;
import com.lyc.demo.controller.dto.AnswerDto;
import com.lyc.demo.entity.Record;
import com.lyc.demo.entity.Test;
import com.lyc.demo.service.IRecordService;
import com.lyc.demo.service.ITestService;
import com.lyc.demo.utils.TokenUtils;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    ITestService testService;

    @Resource
    IRecordService recordService;

    @GetMapping
    public Result getAllQuestions(){
        List<Test> testList = testService.list();
        Random random = new Random();
        while(testList.size()>20)
        {
            int index = random.nextInt(testList.size());
            testList.remove(index);
        }
        return Result.success(testList);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize){
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("question", name);
        queryWrapper.orderByAsc("id");
        return Result.success(testService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneQuestion(@PathVariable Integer id){
        return Result.success(testService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteQuestionBatch(@RequestBody List<Integer> ids){
        return Result.success(testService.removeByIds(ids));

    }

    @PostMapping
    public Result addOrModifyQuestion(@RequestBody Test test){
        return Result.success(testService.saveOrUpdate(test));
    }

    @PostMapping("/submit")
    public Result submitAnswers(@RequestBody List<String> answers){

        for(String answer : answers)
            if(answer==null)
                return Result.error("402", "Uncompleted questions ");
        Integer score = testService.getTestScore(answers);
        Record record = new Record();
        record.setName(TokenUtils.getCurrentUser().getUsername());
        record.setScore(score);
        record.setResult(testService.getScoreTip(score));
        recordService.saveOrUpdate(record);
        return Result.success(testService.getScoreTip(score));

    }

    @GetMapping("/result/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name){
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByAsc("id");

        return Result.success(recordService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }

    @DeleteMapping("/result/{id}")
    public Result deleteRecord(@PathVariable Integer id){
        return Result.success(recordService.removeById(id));
    }

    @PostMapping("/result/del/batch")
    public Result deleteResultBatch(@RequestBody List<Integer> ids){
        return Result.success(recordService.removeByIds(ids));
    }






}
