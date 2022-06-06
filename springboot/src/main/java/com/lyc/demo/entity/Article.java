package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@TableName("article")
public class Article {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String tittle;

    private String content;

    @TableField(value = "publish_time")
    private String publishTime;

    private String author;
}
