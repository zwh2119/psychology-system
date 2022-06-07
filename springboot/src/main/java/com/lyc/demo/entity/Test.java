package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("test_question")
public class Test {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String question;

    @TableField(value = "answer_a")
    private String answerA;

    @TableField(value = "answer_b")
    private String answerB;

    @TableField(value = "answer_c")
    private String answerC;

}
