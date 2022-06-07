package com.lyc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.controller.dto.AnswerDto;
import com.lyc.demo.entity.Test;
import com.lyc.demo.mapper.TestMapper;
import com.lyc.demo.service.ITestService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Override
    public String getScoreTip(Integer score) {
        String text = null;
        if(score < 4)
            text = "心理非常健康，请你放心。";
        else if(score < 8)
            text = "大致还属于健康的范围，但应有所注意，也可以找老师或同学聊聊。";
        else if(score < 15)
            text = "你在心理方面有了一些障碍，应采取适当的方法进行调适，或找心理师帮助你。";
        else if(score < 20)
            text = "是黄牌警告，有可能患了某些心理疾病，应找专门的心理医生进行检查治疗。";
        else
            text = "有较严重的心理障碍，应及时找专门的心理医生治疗。";

        return text;


    }

    @Override
    public Integer getTestScore(List<String> answers) {
        Integer score = 0;
        for(String answer : answers){
            if(answer.equals("answerA"))
                score+=2;
            else if(answer.equals("answerB"))
                score+=1;
            else score+=0;
        }
        return score;
    }
}
