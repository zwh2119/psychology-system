package com.lyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.demo.controller.dto.AnswerDto;
import com.lyc.demo.entity.Test;

import java.util.List;

public interface ITestService extends IService<Test> {

    public String getScoreTip(Integer score);

    public Integer getTestScore(List<String> answers);
}
