package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("article")
public class Article {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String tittle;

    private String content;

    private String publish_time;

    private String author;
}
